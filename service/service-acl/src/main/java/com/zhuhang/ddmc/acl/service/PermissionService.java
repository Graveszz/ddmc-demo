package com.zhuhang.ddmc.acl.service;

import com.zhuhang.ddmc.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> queryAllMenu();

    boolean removeChildById(Long id);

    Map<String, Object> findPermissionByRoleId(Long roleId);

    void saveRolePermissionRelationship(Long roleId, Long[] permissionId);
}
