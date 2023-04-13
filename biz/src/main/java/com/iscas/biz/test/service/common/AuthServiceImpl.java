//package com.iscas.biz.service.common;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.iscas.base.biz.config.Constants;
//import com.iscas.templet.exception.AuthConfigException;
//import com.iscas.templet.exception.LoginException;
//import com.iscas.base.biz.model.auth.Menu;
//import com.iscas.base.biz.model.auth.Role;
//import com.iscas.base.biz.model.auth.Url;
//import com.iscas.base.biz.service.AbstractAuthService;
//import com.iscas.base.biz.util.CaffCacheUtils;
//import com.iscas.base.biz.util.CustomSession;
//import com.iscas.base.biz.util.JWTUtils;
//import com.iscas.base.biz.util.LoginCacheUtils;
//import com.iscas.biz.model.User;
//import com.iscas.common.tools.core.security.AesUtils;
//import com.iscas.common.tools.core.security.MD5Utils;
//import com.iscas.common.tools.xml.Dom4jUtils;
//import com.iscas.common.web.tools.cookie.CookieUtils;
//import com.iscas.templet.common.ResponseEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * 用户认证鉴权service
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/16 18:58
// * @since jdk1.8
// */
//@Service()
//@Slf4j
//public class AuthServiceImpl extends AbstractAuthService {
//    @Autowired
//    private UserService userService;
//    @Cacheable(value = "auth", key="'url_map'")
//    @Override
//    public Map<String, Url> getUrls() throws IOException, AuthConfigException {
//        log.debug("------读取auth配置------");
//        Map<String, Url> result = new HashMap<>();
//        //读取
//        Resource resource = new ClassPathResource(AUTH_CONFIG_XML_NAME);
//        try(
//                InputStream inputStream = resource.getInputStream();
//        ) {
//            Document document = Dom4jUtils.getXMLByInputStream(inputStream);
//            Element rootElement = document.getRootElement();
//
//            //获取URLs
//            result = getUrlMap(rootElement);
//
//            return result;
//        }
//    }
//
//    @Override
//    public String getRoles(String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.eq("username", username);
//        User user = userService.getOne(queryWrapper);
////        return user.getRole();
//        return null;
//    }
//
//    @Override
//    public void invalidToken(HttpServletRequest request) {
//        String token = null;
//        token = request.getHeader(TOKEN_KEY);
//        if (token == null) {
//            //尝试从cookie中拿author
//            Cookie cookie = CookieUtils.getCookieByName(request, TOKEN_KEY);
//            if (cookie != null) {
//                token = cookie.getValue();
//            }
//        }
//        CaffCacheUtils.remove(token);
//        request.getSession().invalidate();
//    }
//
//    @Cacheable(value = "auth", key="'role_map'")
//    @Override
//    public Map<String, Role> getAuth() throws IOException, AuthConfigException {
//        log.debug("------读取auth配置------");
//        Map<String, Role> result = new HashMap<>();
//        //读取
//        Resource resource = new ClassPathResource(AUTH_CONFIG_XML_NAME);
//        try(
//                InputStream inputStream = resource.getInputStream();
//        )
//        {
//            Document document = Dom4jUtils.getXMLByInputStream(inputStream);
//            Element rootElement = document.getRootElement();
//            //获取menus
//            Map<String, Menu> menuMap = getMenuMap(rootElement);
//            //获取URLs
//            Map<String, Url> urlMap = getUrlMap(rootElement);
//            //获取roles节点
//            Element rolesElement = Dom4jUtils.getChildElement(rootElement, "roles");
//            //获得role节点列表
//            List<Element> roleElements = rolesElement.elements("role");
//            if(!CollectionUtils.isEmpty(roleElements)){
//                for (Element roleElement: roleElements) {
//                    Role role = new Role();
//                    String roleKey = roleElement.attributeValue("key");
//                    String roleName = roleElement.attributeValue("name");
//                    role.setKey(roleKey);
//                    role.setName(roleName);
//                    //将配置的menus注入
//                    insertMenus(roleElement,menuMap, role);
//                    //将配置的url注入
//                    insertUrls(roleElement, urlMap, role);
//                    result.put(roleKey, role);
//                }
//            }
//        }
//        return result;
//    }
//
//
//
//
//    private Map<String, Menu> getMenuMap(Element rootElement){
//        Map<String, Menu> menuMap = new HashMap<>();
//        Element menusElement = Dom4jUtils.getChildElement(rootElement, "menus");
//        List<Element> menuElements = menusElement.elements("menu");
//        if(!CollectionUtils.isEmpty(menuElements)){
//            for (Element menuElement : menuElements) {
//                Menu menu = new Menu();
//                String menuKey = menuElement.attributeValue("key");
//                String menuName = menuElement.getTextTrim();
//                menu.setKey(menuKey);
//                menu.setName(menuName);
//                menuMap.put(menuKey, menu);
//            }
//        }
//        return menuMap;
//    }
//
//    private Map<String, Url> getUrlMap(Element rootElement){
//        Map<String, Url> urlMap = new HashMap<>();
//        Element urlsElement = Dom4jUtils.getChildElement(rootElement, "urls");
//        List<Element> urlElements = urlsElement.elements("url");
//        if(!CollectionUtils.isEmpty(urlElements)){
//            for (Element urlElement : urlElements) {
//                Url url = new Url();
//                String urlKey = urlElement.attributeValue("key");
//                String urlName = urlElement.getTextTrim();
//                url.setKey(urlKey);
//                url.setName(urlName);
//                urlMap.put(urlKey, url);
//            }
//        }
//        return urlMap;
//    }
//
//    private void insertMenus(Element roleElement, Map<String, Menu> menuMap, Role role) throws AuthConfigException {
//        //注入menus
//        //获取ref-menus节点
//        Element refMenusElement = roleElement.element("ref-menus");
//        if(refMenusElement != null){
//            //获取 ref-menus 的text
//            String menuKeys = refMenusElement.getTextTrim();
//            if(StringUtils.isNotBlank(menuKeys)){
//                //逗号分割每个menu,并从上面的menuMap中查到对应的值，注入Role
//                String[] menuKeyArray = StringUtils.split(menuKeys,",");
//                for (String menuKey: menuKeyArray) {
//                    Menu menu = menuMap.get(menuKey);
//                    if(menu == null){
//                        throw new AuthConfigException("读取权限配置文件出错",
//                                String.format("读取权限配置文件出错, 未找到role:%s下的ref-menus中的:%s对应的menu配置",role.getKey(),menuKey));
//                    }else{
//                        role.addMenu(menu);
//                    }
//                }
//            }
//        }
//    }
//
//    private void insertUrls(Element roleElement, Map<String, Url> urlMap, Role role) throws AuthConfigException {
//        //注入menus
//        //获取ref-menus节点
//        Element refUrlsElement = roleElement.element("ref-urls");
//        if(refUrlsElement != null){
//            //获取 ref-urls 的text
//            String urlKeys = refUrlsElement.getTextTrim();
//            if(StringUtils.isNotBlank(urlKeys)){
//                //逗号分割每个url,并从上面的urlMap中查到对应的值，注入Role
//                String[] urlKeyArray = StringUtils.split(urlKeys,",");
//                for (String urlKey: urlKeyArray) {
//                    Url url = urlMap.get(urlKey);
//                    if(url == null){
//                        throw new AuthConfigException("读取权限配置文件出错",
//                                String.format("读取权限配置文件出错, 未找到url:%s下的ref-urls中的:%s对应的menu配置",role.getKey(),urlKey));
//                    }else{
//                        role.addUrl(url);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void loginHandler(HttpServletResponse response, Map<String, String> user, ResponseEntity responseEntity, int expire, int cookieExpire) throws LoginException {
//        String pwd = user.get("password");
//        String username = user.get("username");
//        String key = user.get("key");
//        String loginKey = LoginCacheUtils.get(key);
//        if (loginKey == null) {
//            throw new LoginException("未获得加密码，拒绝登录");
//        }
//        LoginCacheUtils.remove(key);
//        try {
//            username = AesUtils.aesDecrypt(username, loginKey);
//            pwd = AesUtils.aesDecrypt(pwd, loginKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new LoginException("非法登陆",e.getMessage());
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        User dbUser = userService.getOne(queryWrapper);
//
//        if (dbUser == null) {
//            throw new LoginException("用户不存在");
//        } else {
//            //加盐校验用户密码
//            boolean verify = false;
//            try {
//                verify = MD5Utils.saltVerify(pwd, dbUser.getPassword());
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new LoginException("校验密码出错");
//            }
//
//            if (!verify) {
//                throw new LoginException("密码错误");
//            }
//
//            String token = null;
//            try {
//                String sessionId = UUID.randomUUID().toString();
//                token = JWTUtils.createToken(username, expire);
//                //清除以前的TOKEN
//                //暂时加上这个处理
//                String tokenold = (String) CaffCacheUtils.get("user-token:" + username);
//                if (tokenold != null) {
//                    CaffCacheUtils.remove(tokenold);
//                }
//                CaffCacheUtils.set("user-token:" + username, token);
//
//                CookieUtils.setCookie(response, TOKEN_KEY, token, cookieExpire);
//                String roleKey = getRoles(username);
//                Map<String, Role> auth = getAuth();
//                Role role = auth.get(roleKey);
//                Map map = new HashMap<>();
////                map.put("permission", dbUser.getRole());
//                map.put(Constants.TOKEN_KEY, token);
////                map.put("role", dbUser.getRole());
//                map.put("userId", dbUser.getId());
//                map.put("username", dbUser.getUsername());
//
//                responseEntity.setValue(map);
//                dbUser.setPassword(null);
//                //创建一个虚拟session
//                CustomSession.setAttribute(sessionId, SESSION_USER, dbUser);
//
//                //处理多用户登陆的问题
////                if (username != null) {
////                    synchronized (username.intern()) {
////                        User dbUser1 = userService.getById(dbUser.getId());
////                        String sessionId = dbUser1.getSessionId();
////                        //如果数据库sessionId部位空，并且session不是当前SESSION
////                        if (sessionId != null && !StringUtils.equals(sessionId, session.getId())) {
////                            HttpSession session1 = MySessionContext.getSession(sessionId);
////                            //使之前的session失效
////                            if (session1 != null) {
////                                session1.invalidate();
////                            }
////
////                        }
////                        dbUser1.setSessionId(session.getId());
////                        userService.updateById(dbUser1);
////
////                        //处理websocket的问题
////                        WebSocketSession webSocketSession = (WebSocketSession) CaffCacheUtils.get("websocketSession:" + dbUser.getUsername());
////                        if (webSocketSession != null) {
////                            webSocketSession.close();
////                        }
////                    }
////                }
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                throw new LoginException("登录时创建token异常", e);
//            } catch (AuthConfigException e) {
//                e.printStackTrace();
//                throw new LoginException("读取权限配置信息出错", e);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new LoginException("登录异常", e);
//            }
//        }
//    }
//
//}
