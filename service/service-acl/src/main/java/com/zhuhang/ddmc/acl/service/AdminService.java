package com.zhuhang.ddmc.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.model.acl.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.vo.acl.AdminQueryVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
public interface AdminService extends IService<Admin> {

    IPage<Admin> selectPage(Page<Admin> adminPage, AdminQueryVo adminQueryVo);

    Admin getByUsername(String username);
}
