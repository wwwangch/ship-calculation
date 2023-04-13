package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.domain.common.*;
import com.iscas.biz.mapper.common.*;
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
 * 菜单service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/22 8:26
 * @since jdk1.8
 */
@SuppressWarnings({"FieldCanBeLocal", "unused", "rawtypes"})
@Service
@ConditionalOnMybatis
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final OprationMapper oprationMapper;
    private final MenuOprationMapper menuOprationMapper;


    public MenuService(RoleMapper roleMapper, MenuMapper menuMapper, RoleMenuMapper roleMenuMapper,
                       OprationMapper oprationMapper, MenuOprationMapper menuOprationMapper) {
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.oprationMapper = oprationMapper;
        this.menuOprationMapper = menuOprationMapper;
    }

    public TreeResponseData<Menu> getTree() {
        List<Menu> menus = this.list();
        TreeResponseData<Menu> root = new TreeResponseData<Menu>()
                .setId("-1")
                .setValue("root")
                .setLabel("菜单");
        if (CollectionUtils.isNotEmpty(menus)) {
            Map<Integer, List<TreeResponseData<Menu>>> childOrgs = getChildMenus(menus);
            combineNode(null, root, childOrgs);
        }
        return root;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private void combineNode(Object pid, TreeResponseData<Menu> treeResponseData, Map<Integer, List<TreeResponseData<Menu>>> childOrgs) {
        List<TreeResponseData<Menu>> treeDataOrgs = childOrgs.get(pid);
        if (CollectionUtils.isNotEmpty(treeDataOrgs)) {
            treeResponseData.setChildren(treeDataOrgs);
            for (TreeResponseData<Menu> treeDataOrg : treeDataOrgs) {
                Integer id = (Integer) treeDataOrg.getId();
                combineNode(id, treeDataOrg, childOrgs);
            }
        }
    }

    private Map<Integer, List<TreeResponseData<Menu>>> getChildMenus(List<Menu> menus) {
        List<RoleMenuKey> roleMenuKeys = roleMenuMapper.selectList(null);
        List<Role> allRoles = roleMapper.selectList(null);
        Map<Integer, List<Role>> menuRoleMap = new HashMap<>(16);
        Map<Integer, Role> roleMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(allRoles)) {
            roleMap = allRoles.stream().collect(Collectors.toMap(Role::getRoleId, role -> role));
        }

        if (CollectionUtils.isNotEmpty(roleMenuKeys)) {
            for (RoleMenuKey roleMenuKey : roleMenuKeys) {
                Role role = roleMap.get(roleMenuKey.getRoleId());
                if (role != null) {
                    menuRoleMap.computeIfAbsent(roleMenuKey.getMenuId(), k -> new ArrayList<>()).add(role);
                }
            }
        }

        List<Map> menuOprationMaps = menuMapper.selectMenuOpration();
        Map<Integer, List<Map>> menuOprationMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(menuOprationMaps)) {
            for (Map oprationMap : menuOprationMaps) {
                Integer menuId = (Integer) oprationMap.get("menu_id");
                menuOprationMap.computeIfAbsent(menuId, k -> new ArrayList<>()).add(oprationMap);
            }
        }

        Map<Integer, List<TreeResponseData<Menu>>> childOrgs = new HashMap<>(16);
        for (Menu menu : menus) {
            Integer menuId = menu.getMenuId();
            Integer menuPid = menu.getMenuPid();

            TreeResponseData<Menu> treeResponseData = new TreeResponseData<>();
            List<Role> rs = menuRoleMap.get(menuId);
            List<Map> menuOprations = menuOprationMap.get(menuId);
            treeResponseData.setLabel(menu.getMenuName())
                    .setId(menu.getMenuId())
                    .setValue(menu.getMenuId())
                    .setData(menu);
            if (CollectionUtils.isNotEmpty(rs)) {
                StringJoiner roleNamesJoiner = new StringJoiner(",");
                for (Role r : rs) {
                    menu.getRoleIds().add(r.getRoleId());
                    roleNamesJoiner.add(r.getRoleName());
                }
                menu.setRoleNames(roleNamesJoiner.toString());
            }
            if (CollectionUtils.isNotEmpty(menuOprations)) {
                StringJoiner opNameJoiner = new StringJoiner(",");
                for (Map menuOpration : menuOprations) {
                    menu.getOprationIds().add((Integer) menuOpration.get("op_id"));
                    opNameJoiner.add((String) menuOpration.get("op_name"));
                }
                menu.setOprationNames(opNameJoiner.toString());
            }
            childOrgs.computeIfAbsent(menuPid, k -> new ArrayList<>()).add(treeResponseData);
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
    public int addMenu(Menu menu) throws ValidDataException {
        AssertObjUtils.assertNull(menu.getMenuId(), "请求参数有误，menuId不能为空");
        ValidatePropDistinctUtils.validateFromMysql(SpringUtils.getBean(StringRaiseUtils.lowerFirst(DynamicMapper.class.getSimpleName())), "menu", "menu_name", menu.getMenuName());
        Date date = new Date();
        menu.setMenuCreateTime(date)
                .setMenuUpdateTime(date);
        this.save(menu);
        List<Integer> roleIds = menu.getRoleIds();
        List<Integer> opIds = menu.getOprationIds();

        //配置角色
        insertRoleIds(roleIds, menu);

        //配置权限标识
        insertOprations(opIds, menu);

        return 1;
    }

    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    public int editMenu(Menu menu) {
        AssertObjUtils.assertNotNull(menu.getMenuId(), "请求参数有误，menuId不能为空");
        Date date = new Date();
        menu.setMenuUpdateTime(date);
        this.updateById(menu);

        //配置角色
        List<Integer> roleIds = menu.getRoleIds();
        roleMenuMapper.delete(new QueryWrapper<RoleMenuKey>().lambda().eq(RoleMenuKey::getMenuId, menu.getMenuId()));
        insertRoleIds(roleIds, menu);

        //配置权限标识
        List<Integer> opIds = menu.getOprationIds();
        LambdaQueryWrapper<MenuOprationKey> queryWrapper = new QueryWrapper<MenuOprationKey>().lambda().eq(MenuOprationKey::getMenuId, menu.getMenuId());
        menuOprationMapper.delete(queryWrapper);
        insertOprations(opIds, menu);

        return 1;
    }

    private void insertRoleIds(List<Integer> roleIds, Menu menu) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Integer roleId : roleIds) {
                RoleMenuKey roleMenuKey = new RoleMenuKey();
                roleMenuKey.setMenuId(menu.getMenuId());
                roleMenuKey.setRoleId(roleId);
                roleMenuMapper.insert(roleMenuKey);
            }
        }
    }

    private void insertOprations(List<Integer> opIds, Menu menu) {
        if (CollectionUtils.isNotEmpty(opIds)) {
            for (Integer opId : opIds) {
                MenuOprationKey menuOprationKey = new MenuOprationKey();
                menuOprationKey.setMenuId(menu.getMenuId());
                menuOprationKey.setOpId(opId);
                menuOprationMapper.insert(menuOprationKey);
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public int deleteMenu(Integer menuId) {
        this.removeById(menuId);
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Throwable.class)
    public void deleteNode(List<Integer> menuIds) {
        menuIds.forEach(this::deleteMenu);
    }
}
