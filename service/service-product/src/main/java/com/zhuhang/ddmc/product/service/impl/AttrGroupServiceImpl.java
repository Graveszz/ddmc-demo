package com.zhuhang.ddmc.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.model.product.AttrGroup;
import com.zhuhang.ddmc.product.mapper.AttrGroupMapper;
import com.zhuhang.ddmc.product.service.AttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuhang.ddmc.vo.product.AttrGroupQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {

    @Override
    public IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo) {
        LambdaQueryWrapper<AttrGroup> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String name = attrGroupQueryVo.getName();
        if (!StringUtils.isEmpty(name)){
            lambdaQueryWrapper.like(AttrGroup::getName,name);
        }
        return baseMapper.selectPage(pageParam,lambdaQueryWrapper);
    }
}
