package com.zhuhang.ddmc.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuhang.ddmc.acl.service.RolePermissionService;
import com.zhuhang.ddmc.acl.utils.PermissionHelper;
import com.zhuhang.ddmc.model.acl.Permission;
import com.zhuhang.ddmc.acl.mapper.PermissionMapper;
import com.zhuhang.ddmc.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuhang.ddmc.model.acl.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    RolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryAllMenu() {
        //获取全部权限数据
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.build(allPermissionList);
        return result;
    }

    //递归删除菜单
    @Override
    public boolean removeChildById(Long id) {
        List<Long> idList = new ArrayList<>();
        this.selectChildListById(id, idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
        return true;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(Long id, List<Long> idList) {
        List<Permission> childList = baseMapper.selectList(new QueryWrapper<Permission>().eq("pid", id).select("id"));
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    @Override
    public Map<String, Object> findPermissionByRoleId(Long roleId) {
        //查询所有的权限
        List<Permission> allPermissionList =baseMapper.selectList(null);

        //角色已拥有的权限
        List<RolePermission> existRolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id", roleId).select("permission_id"));
        List<Long> existPermissionList = existRolePermissionList.stream().map(c->c.getPermissionId()).collect(Collectors.toList());

        //方法1：对Permission进行分类
        //方法2：查询分配角色id对应的角色信息
        List<Permission> assignPermissions = new ArrayList<>();
        for (Permission permission : allPermissionList) {
            //已分配
            if(existPermissionList.contains(permission.getId())) {
                assignPermissions.add(permission);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignPermissions", assignPermissions);
        roleMap.put("allPermissions", allPermissionList);
        return roleMap;
    }

    @Override
    public void saveRolePermissionRelationship(Long roleId, Long[] permissionId) {

    }
}
