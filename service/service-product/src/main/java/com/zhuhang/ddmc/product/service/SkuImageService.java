package com.zhuhang.ddmc.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.product.SkuImage;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> findBySkuId(Long id);
}
