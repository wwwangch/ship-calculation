package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.domain.common.Org;
import com.iscas.biz.domain.common.OrgRoleKey;
import com.iscas.biz.domain.common.Role;
import com.iscas.biz.mapper.common.OrgMapper;
import com.iscas.biz.mapper.common.OrgRoleMapper;
import com.iscas.biz.mapper.common.RoleMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.util.ValidatePropDistinctUtils;
import com.iscas.common.tools.assertion.AssertObjUtils;
import com.iscas.common.tools.core.string.StringRaiseUtils;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.tree.TreeResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 组织机构service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/20 18:10
 * @since jdk1.8
 */
@SuppressWarnings({"FieldCanBeLocal", "unused", "rawtypes", "unchecked"})
@Service
@ConditionalOnMybatis
public class OrgService extends ServiceImpl<OrgMapper, Org> {
    private final OrgMapper orgMapper;
    private final OrgRoleMapper orgRoleMapper;
    private final RoleMapper roleMapper;


    public OrgService(OrgMapper orgMapper, OrgRoleMapper orgRoleMapper, RoleMapper roleMapper) {
        this.orgMapper = orgMapper;
        this.orgRoleMapper = orgRoleMapper;
        this.roleMapper = roleMapper;
    }

    public TreeResponseData<Org> getTree() {
        List<Org> orgs = this.list();
        TreeResponseData<Org> root = new TreeResponseData<Org>()
                .setId("-1")
                .setValue("root")
                .setLabel("组织机构");
        if (CollectionUtils.isNotEmpty(orgs)) {
            Map<Integer, List<TreeResponseData<Org>>> childOrgs = getChildOrgs(orgs);
            combineNode(null, root, childOrgs);
        }
        return root;
    }

    private void combineNode(Integer pid, TreeResponseData treeResponseData, Map<Integer, List<TreeResponseData<Org>>> childOrgs) {
        List<TreeResponseData<Org>> treeDataOrgs = childOrgs.get(pid);
        if (CollectionUtils.isNotEmpty(treeDataOrgs)) {
            treeResponseData.setChildren(treeDataOrgs);
            for (TreeResponseData<Org> treeDataOrg : treeDataOrgs) {
                Integer id = (Integer) treeDataOrg.getId();
                combineNode(id, treeDataOrg, childOrgs);
            }
        }
    }

    private Map<Integer, List<TreeResponseData<Org>>> getChildOrgs(List<Org> orgs) {
        List<OrgRoleKey> orgRoleKeys = orgRoleMapper.selectList(null);
        List<Role> roles = roleMapper.selectList(null);
        Map<Integer, List<Role>> orgRoleMap = new HashMap<>(16);
        Map<Integer, Role> roleMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(roles)) {
            roleMap = roles.stream().collect(Collectors.toMap(Role::getRoleId, role -> role));
        }

        if (CollectionUtils.isNotEmpty(orgRoleKeys)) {
            for (OrgRoleKey orgRoleKey : orgRoleKeys) {
                Role role = roleMap.get(orgRoleKey.getRoleId());
                if (role != null) {
                    orgRoleMap.computeIfAbsent(orgRoleKey.getOrgId(), k -> new ArrayList<>()).add(role);
                }
            }
        }

        Map<Integer, List<TreeResponseData<Org>>> childOrgs = new HashMap<>(16);
        for (Org org : orgs) {
            Integer orgId = org.getOrgId();
            Integer orgPid = org.getOrgPid();

            TreeResponseData<Org> comboboxData = new TreeResponseData<>();
            List<Role> rs = orgRoleMap.get(orgId);
            comboboxData.setLabel(org.getOrgName())
                    .setId(orgId)
                    .setValue(orgId)
                    .setData(org);
            if (CollectionUtils.isNotEmpty(rs)) {
                StringJoiner roleNamesJoiner = new StringJoiner(",");
                for (Role r : rs) {
                    org.getRoleIds().add(r.getRoleId());
                    roleNamesJoiner.add(r.getRoleName());
                }
                org.setRoleNames(roleNamesJoiner.toString());
            }
            childOrgs.computeIfAbsent(orgPid, k -> new ArrayList<>()).add(comboboxData);
        }
        return childOrgs;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public int addOrg(Org org) throws ValidDataException {
        AssertObjUtils.assertNull(org.getOrgId(), "请求参数有误，orgId必须为空");
        ValidatePropDistinctUtils.validateFromMysql(SpringUtils.getBean(StringRaiseUtils.lowerFirst(DynamicMapper.class.getSimpleName())), "org", "org_name", org.getOrgName());
        Date date = new Date();
        org.setOrgCreateTime(date).setOrgUpdateTime(date);
        this.save(org);
        List<Integer> roleIds = org.getRoleIds();
        //配置角色
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Integer roleId : roleIds) {
                OrgRoleKey orgRoleKey = new OrgRoleKey();
                orgRoleKey.setOrgId(org.getOrgId());
                orgRoleKey.setRoleId(roleId);
                orgRoleMapper.insert(orgRoleKey);
            }
        }
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public int editOrg(Org org) {
        AssertObjUtils.assertNotNull(org.getOrgId(), "请求参数有误，orgId不能为空");
        Date date = new Date();
        org.setOrgUpdateTime(date);
        this.updateById(org);
        //配置角色
        List<Integer> roleIds = org.getRoleIds();
        orgRoleMapper.delete(new QueryWrapper<OrgRoleKey>().lambda().eq(OrgRoleKey::getOrgId, org.getOrgId()));
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Integer roleId : roleIds) {
                OrgRoleKey orgRoleKey = new OrgRoleKey();
                orgRoleKey.setOrgId(org.getOrgId());
                orgRoleKey.setRoleId(roleId);
                orgRoleMapper.insert(orgRoleKey);
            }
        }
        return 1;
    }

    @SuppressWarnings("UnusedReturnValue")
    public int deleteOrg(Integer orgId) {
        this.removeById(orgId);
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    public void deleteNode(List<Integer> orgIds) {
        orgIds.forEach(this::deleteOrg);
    }
}
