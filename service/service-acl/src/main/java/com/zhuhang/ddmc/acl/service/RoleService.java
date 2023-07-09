package com.zhuhang.ddmc.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.model.acl.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.vo.acl.RoleQueryVo;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
public interface  RoleService extends IService<Role> {

    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);
}
