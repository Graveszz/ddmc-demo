package com.zhuhang.ddmc.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.zhuhang.ddmc.model.sys.Region;
import com.zhuhang.ddmc.sys.mapper.RegionMapper;
import com.zhuhang.ddmc.sys.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public List<Region> findRegionByKeyword(String keyword) {
        LambdaQueryWrapper<Region> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            lambdaQueryWrapper.like(Region::getName,keyword);
        }
        return baseMapper.selectList(lambdaQueryWrapper);

    }
}
