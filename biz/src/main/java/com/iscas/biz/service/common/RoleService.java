package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.config.Constants;
import com.iscas.biz.domain.common.*;
import com.iscas.biz.mapper.common.RoleMapper;
import com.iscas.biz.mapper.common.RoleMenuMapper;
import com.iscas.biz.mapper.common.RoleOprationMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.common.tools.assertion.AssertObjUtils;
import com.iscas.common.tools.core.string.StringRaiseUtils;
import com.iscas.common.tools.exception.lambda.Lambdas;
import com.iscas.templet.view.table.ComboboxData;
import com.iscas.templet.view.tree.TreeResponseData;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/21 19:50
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service
@ConditionalOnMybatis
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final MenuService menuService;
    private final RoleOprationMapper roleOprationMapper;

    public RoleService(RoleMapper roleMapper, RoleMenuMapper roleMenuMapper, MenuService menuService, RoleOprationMapper roleOprationMapper) {
        this.roleMapper = roleMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.menuService = menuService;
        this.roleOprationMapper = roleOprationMapper;
    }

    public List<ComboboxData> combobox() {
        List<Role> roles = roleMapper.selectList(null);
        if (CollectionUtils.isNotEmpty(roles)) {
            return roles.stream().map(Lambdas.wrapperFunction(role -> new ComboboxData()
                    .setLabel(role.getRoleName())
                    .setValue(role.getRoleId())
                    .setData(role)
            )).toList();
        }
        return new ArrayList<>();
    }

    public TreeResponseData getMenuTree(Integer roleId) {
        Role role = this.getById(roleId);
        AssertObjUtils.assertNotNull(role, StringRaiseUtils.format("角色ID：{}不存在", roleId));

        TreeResponseData<Menu> tree = menuService.getTree();
        List<RoleMenuKey> roleMenuKeys = roleMenuMapper.selectList(new QueryWrapper<RoleMenuKey>().lambda()
                .eq(RoleMenuKey::getRoleId, roleId));
        if (CollectionUtils.isNotEmpty(roleMenuKeys)) {
            List<Integer> menuIds = roleMenuKeys.stream().map(RoleMenuKey::getMenuId).toList();
            selectTree(tree, menuIds);
        }
        return tree;
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateMenuTree(TreeResponseData treeResponseData, Integer roleId) {
        Role role = this.getById(roleId);
        AssertObjUtils.assertNotNull(role, StringRaiseUtils.format("角色ID：{}不存在", roleId));
        //判断超级管理员角色
        AssertObjUtils.assertNotEquals(role.getRoleName(), Constants.SUPER_ROLE_KEY, "超级管理员角色不允许修改");
        List<Integer> menuIds = new ArrayList<>();
        getSelectedMenuIds(treeResponseData, menuIds);
        roleMenuMapper.delete(new QueryWrapper<RoleMenuKey>().lambda().eq(RoleMenuKey::getRoleId, roleId));
        if (CollectionUtils.isNotEmpty(menuIds)) {
            menuIds.stream().map(menuId -> new RoleMenuKey(roleId, menuId)).forEach(roleMenuMapper::insert);

        }
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private void selectTree(TreeResponseData<Menu> tree, List<Integer> menuIds) {
        Object id = tree.getId();
        if (id != null && menuIds.contains(id)) {
            tree.setSelected(true);
        }
        List<TreeResponseData<Menu>> children = tree.getChildren();
        if (CollectionUtils.isNotEmpty(children)) {
            children.forEach(tr -> selectTree(tr, menuIds));
        }
    }

    private void getSelectedMenuIds(TreeResponseData treeResponseData, List<Integer> menuIds) {
        if (treeResponseData.getSelected() && !Objects.equals(treeResponseData.getId(), -1)) {
            menuIds.add((Integer) treeResponseData.getId());
        }
        List<TreeResponseData> children = treeResponseData.getChildren();
        if (CollectionUtils.isNotEmpty(children)) {
            for (TreeResponseData child : children) {
                getSelectedMenuIds(child, menuIds);
            }
        }
    }

    public List<Opration> getOprations(Integer roleId) {
        return roleMapper.selectOprationByRoleId(roleId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editOpration(List<Opration> oprations, Integer roleId) {
        Role role = this.getById(roleId);
        AssertObjUtils.assertNotNull(role, StringRaiseUtils.format("角色ID：{}不存在", roleId));
        //判断超级管理员角色
        AssertObjUtils.assertNotEquals(role.getRoleName(), Constants.SUPER_ROLE_KEY, "超级管理员角色不允许修改");
        roleOprationMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(oprations)) {
            List<RoleOprationKey> roleOprationKeys = oprations.stream().map(opration -> new RoleOprationKey(roleId, opration.getOpId())).toList();
            roleOprationMapper.insertBatch(roleOprationKeys);
        }
    }
}
