package com.zhuhang.ddmc.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.product.SkuPoster;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> findBySkuId(Long id);
}
