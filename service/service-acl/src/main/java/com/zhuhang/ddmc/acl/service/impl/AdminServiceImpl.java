package com.zhuhang.ddmc.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.model.acl.Admin;
import com.zhuhang.ddmc.acl.mapper.AdminMapper;
import com.zhuhang.ddmc.acl.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuhang.ddmc.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public IPage<Admin> selectPage(Page<Admin> adminPage, AdminQueryVo adminQueryVo) {

        String name = adminQueryVo.getUsername();
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            lambdaQueryWrapper.like(Admin::getUsername,name).or().like(Admin::getName,name);
        }
        return baseMapper.selectPage(adminPage,lambdaQueryWrapper);

    }

    @Override
    public Admin getByUsername(String username) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }
}
