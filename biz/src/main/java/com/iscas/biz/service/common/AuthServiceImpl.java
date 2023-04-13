package com.iscas.biz.service.common;

import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.model.auth.Menu;
import com.iscas.base.biz.model.auth.Role;
import com.iscas.base.biz.model.auth.Url;
import com.iscas.base.biz.service.AbstractAuthService;
import com.iscas.base.biz.service.IAuthCacheService;
import com.iscas.base.biz.util.AuthUtils;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.base.biz.util.CustomSession;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.biz.domain.common.User;
import com.iscas.biz.mapper.common.MenuMapper;
import com.iscas.biz.mapper.common.ResourceMapper;
import com.iscas.biz.mapper.common.RoleMapper;
import com.iscas.biz.mapper.common.UserMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.common.tools.core.security.AesUtils;
import com.iscas.common.tools.core.security.MD5Utils;
import com.iscas.common.tools.exception.lambda.Lambdas;
import com.iscas.common.web.tools.cookie.CookieUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.LoginException;
import com.iscas.templet.view.tree.TreeResponseData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户认证鉴权service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 18:58
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service()
@Slf4j
@ConditionalOnMybatis
public class AuthServiceImpl extends AbstractAuthService {
    private static final String CACHE_KEY_LOGIN_ERROR_COUNT = "CACHE_KEY_LOGIN_ERROR_COUNT";
    private static final String CACHE_KEY_USER_LOCK = "CACHE_KEY_USER_LOCK";
    private static final int MAX_LOGIN_ERROR_COUNT = 5;
    private final IAuthCacheService authCacheService;
    private final ResourceMapper resourceMapper;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final UserMapper userMapper;
    private final MenuService menuService;
    private final TokenProps tokenProps;


    public AuthServiceImpl(IAuthCacheService authCacheService, ResourceMapper resourceMapper,
                           RoleMapper roleMapper, MenuMapper menuMapper, UserMapper userMapper, MenuService menuService, TokenProps tokenProps) {
        this.authCacheService = authCacheService;
        this.resourceMapper = resourceMapper;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.userMapper = userMapper;
        this.menuService = menuService;
        this.tokenProps = tokenProps;
    }

    @Cacheable(value = "permission", key = "'url_map'")
    @Override
    public Map<String, Url> getUrls() {
        log.debug("------读取url信息------");
        return Optional.ofNullable(resourceMapper.selectList(null))
                .map(resources -> resources.stream().map(Lambdas.wrapperFunction(resource -> new Url().setKey(String.valueOf(resource.getResourceId()))
                        .setName(resource.getResourceUrl()))).collect(Collectors.toMap(Url::getKey, url -> url)))
                .orElse(Map.of());
    }

    @Cacheable(value = "permission", key = "'menus'")
    @Override
    public List<Menu> getMenus() {
        return Optional.ofNullable(menuMapper.selectList(null))
                .map(dbMenus -> dbMenus.stream().map(Lambdas.wrapperFunction(m ->
                        new Menu().setName(m.getMenuName()).setKey(String.valueOf(m.getMenuId())))).toList())
                .orElse(List.of());
    }


    @Cacheable(value = "permission", key = "'username:'.concat(#username)")
    @Override
    public List<Role> getRoles(String username) {
        Map<String, Role> auth = getAuth();
        return Optional.ofNullable(userMapper.selectUserRoleByUsername(username))
                .map(userRoleMaps -> userRoleMaps.stream()
                        .filter(userRoleMap -> getOneRole(userRoleMap, auth) != null)
                        .map(userRoleMap -> getOneRole(userRoleMap, auth)).toList())
                .orElse(List.of());
    }


    @Override
    public void invalidToken(HttpServletRequest request) {
        Optional.ofNullable(AuthUtils.getToken())
//                .ifPresent(token -> authCacheService.remove(token, Constants.AUTH_CACHE));
                .ifPresent(token -> CacheUtils.evictCache(token, Constants.AUTH_CACHE));
        request.getSession().invalidate();
    }

    @Cacheable(value = "permission", key = "'role_map'")
    @Override
    public Map<String, Role> getAuth() {
        log.debug("------读取角色信息------");
        Map<String, Role> result = new HashMap<>(2 << 6);
        List<com.iscas.biz.domain.common.Role> commonRoles = roleMapper.selectList(null);
        Map<String, Url> urls = getUrls();
        List<Map> menuRoles = menuMapper.selectMenuRole();
        List<Map> roleResources = roleMapper.selectRoleResource();

        Map<Integer, List<Menu>> menuRoleMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(menuRoles)) {
            for (Map menuRole : menuRoles) {
                int roleId = (int) menuRole.get("role_id");
                int menuId = (int) menuRole.get("menu_id");
                String menuName = (String) menuRole.get("menu_name");
                menuRoleMap.computeIfAbsent(roleId, k -> new ArrayList<>())
                        .add(new Menu().setKey(String.valueOf(menuId)).setName(menuName));
            }
        }
        Map<Integer, List<Url>> urlRoleMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(roleResources)) {
            for (Map roleResource : roleResources) {
                int roleId = (int) roleResource.get("role_id");
                int resourceId = (int) roleResource.get("resource_id");
                Url url = urls.get(String.valueOf(resourceId));
                if (url != null) {
                    urlRoleMap.computeIfAbsent(roleId, k -> new ArrayList<>()).add(url);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(commonRoles)) {
            result = commonRoles.stream().map(Lambdas.wrapperFunction(r ->
                            new Role().setKey(String.valueOf(r.getRoleId()))
                                    .setName(r.getRoleName())
                                    .setMenus(menuRoleMap.get(r.getRoleId()))
                                    .setUrls(urlRoleMap.get(r.getRoleId()))))
                    .collect(Collectors.toMap(Role::getKey, r -> r));
        }
        return result;
    }

    @Override
    public void loginHandler(HttpServletResponse response, Map<String, String> user, ResponseEntity responseEntity, int expire, int cookieExpire) throws LoginException {
        String pwd = user.get("password");
        String username = user.get("username");
        String uuid = user.get("key");
//        String loginKey = (String) authCacheService.get(secKey, Constants.LOGIN_CACHE);
        String secretKey = CacheUtils.getCache(Constants.CAPTCHA_CACHE, uuid, String.class);
        if (secretKey == null) {
            throw Exceptions.loginException("未获得加密码，拒绝登录");
        }
//        authCacheService.remove(uuid, Constants.LOGIN_CACHE);
        CacheUtils.evictCache(Constants.CAPTCHA_CACHE, uuid);
        try {
            username = Objects.requireNonNull(AesUtils.aesDecrypt(username, secretKey)).trim();
            pwd = Objects.requireNonNull(AesUtils.aesDecrypt(pwd, secretKey)).trim();
        } catch (Exception e) {
            throw Exceptions.loginException("非法登陆", e);
        }
        String userLockedKey = CACHE_KEY_USER_LOCK + "_" + username;
        String userLoginErrorCountKey = CACHE_KEY_LOGIN_ERROR_COUNT + "_" + username;
//        if (authCacheService.get(userLockedKey, Constants.AUTH_CACHE) != null) {
        if (CacheUtils.getCache(Constants.LOCK_USER_CACHE, userLockedKey, String.class) != null) {
            throw Exceptions.loginException("用户登录连续失败次数过多，已被锁定，自动解锁时间2分钟");
        }
//        Integer errCount = (Integer) authCacheService.get(userLoginErrorCountKey, Constants.AUTH_CACHE);
        Integer errCount = CacheUtils.getCache(Constants.AUTH_CACHE, userLoginErrorCountKey, Integer.class);
        if (errCount != null && errCount >= MAX_LOGIN_ERROR_COUNT) {
            CacheUtils.putCache(LOCK_USER_CACHE, userLockedKey, "locked");
//            authCacheService.set(userLockedKey, "locked", Constants.AUTH_CACHE, 120);
            throw new LoginException("用户登录连续失败次数过多，已被锁定，自动解锁时间2分钟");
        }

        User dbUser = userMapper.selectByUserName(username);
        if (dbUser == null) {
            throw Exceptions.loginException("用户不存在");
        } else {
            //加盐校验用户密码
            boolean verify;
            try {
                verify = MD5Utils.saltVerify(pwd, dbUser.getUserPwd());
                if (!verify) {
//                    Integer count = (Integer) authCacheService.get(userLoginErrorCountKey, Constants.AUTH_CACHE);
                    Integer count = CacheUtils.getCache(Constants.AUTH_CACHE, userLoginErrorCountKey, Integer.class);
                    int errorCount = count == null ? 1 : count + 1;
                    CacheUtils.putCache(AUTH_CACHE, userLoginErrorCountKey, errorCount);
//                    authCacheService.set(userLoginErrorCountKey, errorCount, Constants.AUTH_CACHE, (int) tokenProps.getExpire().getSeconds());
                    throw Exceptions.loginException("密码错误");
                }
            } catch (LoginException e) {
                throw e;
            } catch (Exception e) {
                throw Exceptions.loginException("校验密码出错");
            }
            //生成token
            createToken(response, dbUser.getUserId(), username, responseEntity, expire, cookieExpire, userLockedKey, userLoginErrorCountKey);
        }
    }

    public void createToken(HttpServletResponse response, Integer userId, String username, ResponseEntity responseEntity, int expire, int cookieExpire, String userLockedKey, String userLoginErrorCountKey) throws LoginException {
        String token;
        //noinspection AlibabaRemoveCommentedCode,CommentedOutCode
        try {
            String sessionId = UUID.randomUUID().toString();

            token = JWTUtils.createToken(userId + ";" + username, expire, tokenProps.getCreatorMode());
            //清除以前的TOKEN
            //修改逻辑，改为适应一个用户允许多会话，数目配置在配置文件
            authCacheService.rpush("user-token:" + username, token, Constants.AUTH_CACHE);

            if (tokenProps.isCookieStore()) {
                CookieUtils.setCookie(response, TOKEN_KEY, token, cookieExpire);
            }
            List<Role> roles = getRoles(username);

            Map map = new HashMap<>(2 << 2);
            List<String> menus;
            List<Menu> menuList = new ArrayList<>();
            for (Role role : roles) {
                if (Objects.equals(role.getName(), Constants.SUPER_ROLE_KEY)) {
                    //超级管理员角色
                    List<Menu> dbMenus = getMenus();
                    if (CollectionUtils.isNotEmpty(dbMenus)) {
                        menuList.addAll(dbMenus);
                    }
                    break;
                } else {
                    List<Menu> roleMenus = role.getMenus();
                    if (roleMenus != null) {
                        menuList.addAll(roleMenus);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(menuList)) {
                menuList.add(new Menu("-1", "首页"));
                //获取菜单名并去重
                menus = menuList.stream().map(Menu::getName).distinct().toList();
                //修改返回菜单的数据结构
                TreeResponseData<com.iscas.biz.domain.common.Menu> tree = menuService.getTree();
                TreeResponseData<com.iscas.biz.domain.common.Menu> finalMenus = getFinalMenus(tree, menus);
                map.put("menu", finalMenus);
            }
            map.put(Constants.TOKEN_KEY, token);
            map.put("username", username);

            responseEntity.setValue(map);
            //创建一个虚拟session（没用）
            CustomSession.setAttribute(sessionId, SESSION_USER, username);

//            authCacheService.remove(userLockedKey, Constants.AUTH_CACHE);
            CacheUtils.evictCache(LOCK_USER_CACHE, userLockedKey);
//            authCacheService.remove(userLoginErrorCountKey, Constants.AUTH_CACHE);
            CacheUtils.evictCache(Constants.AUTH_CACHE, userLoginErrorCountKey);

            //处理多用户登陆的问题
//                if (username != null) {
//                    synchronized (username.intern()) {
//                        User dbUser1 = userService.getById(dbUser.getId());
//                        String sessionId = dbUser1.getSessionId();
//                        //如果数据库sessionId部位空，并且session不是当前SESSION
//                        if (sessionId != null && !StringUtils.equals(sessionId, session.getId())) {
//                            HttpSession session1 = MySessionContext.getSession(sessionId);
//                            //使之前的session失效
//                            if (session1 != null) {
//                                session1.invalidate();
//                            }
//
//                        }
//                        dbUser1.setSessionId(session.getId());
//                        userService.updateById(dbUser1);
//
//                        //处理websocket的问题
//                        WebSocketSession webSocketSession = (WebSocketSession) CaffCacheUtils.get("websocketSession:" + dbUser.getUsername());
//                        if (webSocketSession != null) {
//                            webSocketSession.close();
//                        }
//                    }
//                }

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw Exceptions.loginException("登录时创建token异常", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new LoginException("登录异常", e);
        }
    }

    private TreeResponseData<com.iscas.biz.domain.common.Menu> getFinalMenus(TreeResponseData<com.iscas.biz.domain.common.Menu> tree, List<String> menus) {

        Optional.ofNullable(tree).map(TreeResponseData::getData).ifPresent(t -> tree.setPath(tree.getData().getMenuPage()));

        assert tree != null;
        List<TreeResponseData<com.iscas.biz.domain.common.Menu>> children = tree.getChildren();
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        List<TreeResponseData<com.iscas.biz.domain.common.Menu>> copyChildren = new ArrayList<>();
        children.forEach(child -> copyChildren.add(child.clone()));
        tree.setChildren(copyChildren);

        children.forEach(child -> {
            if (child == null) {
                return;
            }
            if (!menus.contains(child.getData().getMenuName())) {
                copyChildren.remove(child);
            }
        });
        copyChildren.forEach(child -> getFinalMenus(child, menus));
        return tree;
    }

    private Role getOneRole(Map userRoleMap, Map<String, Role> auth) {
        return auth.get(String.valueOf(userRoleMap.get("role_id")));
    }
}
