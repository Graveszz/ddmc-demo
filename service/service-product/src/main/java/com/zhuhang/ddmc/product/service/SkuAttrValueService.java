package com.zhuhang.ddmc.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.product.SkuAttrValue;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SkuAttrValue> findBySkuId(Long id);
}
