package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.biz.domain.common.*;
import com.iscas.biz.mapper.common.OrgMapper;
import com.iscas.biz.mapper.common.OrgUserMapper;
import com.iscas.biz.mapper.common.UserMapper;
import com.iscas.biz.mapper.common.UserRoleMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.common.tools.assertion.AssertObjUtils;
import com.iscas.common.tools.core.security.MD5Utils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import com.iscas.templet.view.table.TableSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户Service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/22 15:04
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Service
@Slf4j
@ConditionalOnMybatis
public class UserService extends ServiceImpl<UserMapper, User> {
    @Value("${user_default_pwd:123456}")
    private String userDefaultPwd;

    private final String tableIdentity = "user";
    private final TableDefinitionService tableDefinitionService;

    private final UserRoleMapper userRoleMapper;
    private final OrgMapper orgMapper;
    private final UserMapper userMapper;
    private final OrgUserMapper orgUserMapper;

    public UserService(TableDefinitionService tableDefinitionService, UserRoleMapper userRoleMapper, OrgMapper orgMapper,
                       UserMapper userMapper, OrgUserMapper orgUserMapper) {
        this.tableDefinitionService = tableDefinitionService;
        this.userRoleMapper = userRoleMapper;
        this.orgMapper = orgMapper;
        this.userMapper = userMapper;
        this.orgUserMapper = orgUserMapper;
    }

    private void getAllChildOrgIds(Integer orgId, Map<Integer, List<Org>> allOrgMap, Set<Integer> childOrgIds) {
        childOrgIds.add(orgId);
        List<Org> orgs = allOrgMap.get(orgId);
        if (orgs != null) {
            for (Org org : orgs) {
                getAllChildOrgIds(org.getOrgId(), allOrgMap, childOrgIds);
            }
        }
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public ResponseEntity search(TableSearchRequest request, Integer orgId) throws ValidDataException {
        List<Org> allOrgs = orgMapper.selectList(null);
        String dynamicSql = null;
        if (orgId != null) {
            //找到组织机构的级联下级组织机构，组织机构主键定义有问题，暂时先这么处理
            Set<Integer> childOrgIds = new HashSet<>();
            Map<Integer, List<Org>> childOrgMap = new HashMap<>(16);
            if (CollectionUtils.isNotEmpty(allOrgs)) {
                for (Org allOrg : allOrgs) {
                    Integer orgPid = allOrg.getOrgPid();
                    childOrgMap.computeIfAbsent(orgPid, k -> new ArrayList<>()).add(allOrg);
                }
            }
            getAllChildOrgIds(orgId, childOrgMap, childOrgIds);
            if (CollectionUtils.isNotEmpty(childOrgIds)) {
                StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
                for (Integer childOrgId : childOrgIds) {
                    stringJoiner.add(childOrgId + "");
                }
                dynamicSql = " user_id in (" +
                        "select distinct t1.user_id from user_info t1, org_user t2, org t3 where t1.user_id = t2.user_id and t2.org_id = t3.org_id and t3.org_id in " +
                        stringJoiner +
                        ") ";
            }
        }

        TableResponse<Map> tableResponse = tableDefinitionService.getData(tableIdentity, request, null, dynamicSql);
        TableResponseData value = tableResponse.getValue();
        List<Map> data = (List<Map>) value.getData();
        if (CollectionUtils.isNotEmpty(data)) {
            //获取这些用户的角色信息
            List<Map> userRoleMaps = userMapper.selectUserRole();
            Map<Integer, List<Map>> userRoleMap = new HashMap<>(16);
            if (CollectionUtils.isNotEmpty(userRoleMaps)) {
                for (Map roleMap : userRoleMaps) {
                    Integer userId = (Integer) roleMap.get("user_id");
                    userRoleMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(roleMap);
                }
            }
            for (Map datum : data) {
                Integer userId = (Integer) datum.get("user_id");
                if (userRoleMap.containsKey(userId)) {
                    StringJoiner userRoleJoiner = new StringJoiner(",");
                    List<Integer> roles = new ArrayList<>();
                    List<Map> userRoles = userRoleMap.get(userId);
                    for (Map userRole : userRoles) {
                        roles.add((Integer) userRole.get("role_id"));
                        userRoleJoiner.add((String) userRole.get("role_name"));
                    }
                    datum.put("user_role", userRoleJoiner.toString());
                    datum.put("user_role_edit", roles);
                }
            }

            //获取这些用户的组织机构信息
            Map<Integer, Org> allOrgMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(allOrgs)) {
                allOrgMap = allOrgs.stream().collect(Collectors.toMap(Org::getOrgId, org -> org));
            }
            List<Map> orgUserMaps = userMapper.selectOrgUser();
            Map<Integer, List<Map>> orgUserMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(orgUserMaps)) {
                for (Map userMap : orgUserMaps) {
                    Integer userId = (Integer) userMap.get("user_id");
                    orgUserMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(userMap);
                }
            }
            for (Map datum : data) {
                Integer userId = (Integer) datum.get("user_id");
                if (orgUserMap.containsKey(userId)) {
                    StringJoiner orgUserJoiner = new StringJoiner(",");
                    List<Integer> orgs = new ArrayList<>();
                    List<Map> orgUsers = orgUserMap.get(userId);
                    for (Map orgUser : orgUsers) {
                        orgs.add((Integer) orgUser.get("org_id"));
                        Stack<String> orgNameStack = new Stack<>();
                        getParentOrgName((Integer) orgUser.get("org_id"), allOrgMap, orgNameStack);
                        StringJoiner stringJoiner = new StringJoiner("/");
                        while (orgNameStack.size() > 0) {
                            stringJoiner.add(orgNameStack.pop());
                        }
                        orgUserJoiner.add(stringJoiner.toString());
                    }
                    datum.put("user_org", orgUserJoiner.toString());
                    datum.put("user_org_edit", orgs);
                }
            }

        }
        return tableResponse;
    }

    private void getParentOrgName(Integer orgId, Map<Integer, Org> allOrgMap, Stack<String> orgNameStack) {
        if (orgId == null) {
            return;
        }
        Org org = allOrgMap.get(orgId);
        String orgName = org.getOrgName();
        orgNameStack.push(orgName);
        Integer orgPid = org.getOrgPid();
        getParentOrgName(orgPid, allOrgMap, orgNameStack);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    public ResponseEntity add(Map<String, Object> data) throws NoSuchAlgorithmException, ValidDataException {
        Map<String, Object> forceItem = new HashMap<>();
        forceItem.put("user_pwd", MD5Utils.saltMD5(userDefaultPwd));

        List<Integer> roleIds = (List<Integer>) data.remove("user_role_edit");
        List<Integer> orgsIds = (List<Integer>) data.remove("user_org_edit");
        ResponseEntity responseEntity = tableDefinitionService.saveData(tableIdentity, data, false, null, forceItem);
        //新增用户-角色
        int userId = ((BigInteger) data.get("id")).intValue();
        insertUserRole(roleIds, userId);

        //新增用户-组织机构
        insertOrgUser(orgsIds, userId);
        return responseEntity;
    }

    public ResponseEntity edit(Map<String, Object> data) throws ValidDataException {
        List<Integer> roleIds = (List<Integer>) data.remove("user_role_edit");
        List<Integer> orgIds = (List<Integer>) data.remove("user_org_edit");
        ResponseEntity responseEntity = tableDefinitionService.saveData(tableIdentity, data, false, null, null);
        //删除原有的此user相关的 user_role中数据，插入新的
        int userId = (int) data.get("user_id");
        userRoleMapper.delete(new QueryWrapper<UserRoleKey>().lambda().eq(UserRoleKey::getUserId, userId));
        insertUserRole(roleIds, userId);
        orgUserMapper.delete(new QueryWrapper<OrgUserKey>().lambda().eq(OrgUserKey::getUserId, userId));

        insertOrgUser(orgIds, userId);
        return responseEntity;
    }

    private void insertUserRole(List<Integer> roleIds, int userId) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Integer roleId : roleIds) {
                UserRoleKey userRoleKey = new UserRoleKey();
                userRoleKey.setUserId(userId);
                userRoleKey.setRoleId(roleId);
                userRoleMapper.insert(userRoleKey);
            }
        }
    }

    private void insertOrgUser(List<Integer> orgIds, int userId) {
        if (CollectionUtils.isNotEmpty(orgIds)) {
            for (Integer orgId : orgIds) {
                if (Objects.equals(-1, orgId)) {
                    continue;
                }
                OrgUserKey orgUserKey = new OrgUserKey();
                orgUserKey.setUserId(userId);
                orgUserKey.setOrgId(orgId);
                orgUserMapper.insert(orgUserKey);
            }
        }
    }

    public void changePwd(Integer userId, Map<String, Object> data) throws NoSuchAlgorithmException, BaseException {
        String loginUsername = JWTUtils.getLoginUsername();
        User user;
        if (loginUsername != null) {
            //判断登录的用户是否和当前的userId一致
            user = userMapper.selectByUserName(loginUsername);
            if (user == null) {
                throw Exceptions.formatBaseException("未找到当前登录用户:[{0}]信息", loginUsername);
            }
            if (!Objects.equals(user.getUserId(), userId)) {
                throw Exceptions.formatBaseException("userId:[{0}]与登录的用户[{1}]不一致", userId, user.getUserName());
            }
        } else {
            throw Exceptions.baseException("未找到当前登录信息，可能为未携带token或token有误");
        }
        AssertObjUtils.assertNotNull(user, "此用户不存在");
        String oldPwd = (String) data.get("oldPwd");
        String newPwd = (String) data.get("newPwd");
        boolean b = MD5Utils.saltVerify(oldPwd, user.getUserPwd());
        if (!b) {
            throw Exceptions.baseException("旧密码验证失败");
        }
        user.setUserPwd(MD5Utils.saltMD5(newPwd));
        userMapper.updatePwd(user);
    }


    public void deleteCache(List<Object> ids) {
        List<User> users = userMapper.selectUserByIds(ids);
        if (users != null) {
            users.forEach(user -> deleteOneUserCache(user.getUserName()));
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'username:'.concat(#username)")
    })
    public void deleteOneUserCache(String username) {
        log.debug("删除用户:{}的权限相关缓存", username);
    }
}
