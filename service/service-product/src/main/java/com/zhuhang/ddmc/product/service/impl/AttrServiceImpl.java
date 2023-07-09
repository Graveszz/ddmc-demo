package com.zhuhang.ddmc.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuhang.ddmc.model.product.Attr;
import com.zhuhang.ddmc.product.mapper.AttrMapper;
import com.zhuhang.ddmc.product.service.AttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Override
    public List<Attr> findByAttrGroupId(Long attrGroupId) {
//        QueryWrapper queryWrapper = new QueryWrapper();
        LambdaQueryWrapper<Attr> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Attr::getAttrGroupId,attrGroupId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
