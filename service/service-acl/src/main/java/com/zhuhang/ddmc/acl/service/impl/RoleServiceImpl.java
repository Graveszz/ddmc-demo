package com.zhuhang.ddmc.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.model.acl.Role;
import com.zhuhang.ddmc.acl.mapper.RoleMapper;
import com.zhuhang.ddmc.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuhang.ddmc.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        String name = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            lambdaQueryWrapper.like(Role::getRoleName,name);
        }
        return baseMapper.selectPage(pageParam, lambdaQueryWrapper);

    }
}
