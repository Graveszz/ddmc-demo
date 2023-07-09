package com.zhuhang.ddmc.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.product.Attr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface AttrService extends IService<Attr> {

    List<Attr> findByAttrGroupId(Long attrGroupId);
}
