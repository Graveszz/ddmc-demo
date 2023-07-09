package com.zhuhang.ddmc.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.sys.Region;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface RegionService extends IService<Region> {

    List<Region> findRegionByKeyword(String keyword);
}
