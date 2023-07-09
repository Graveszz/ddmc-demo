package com.zhuhang.ddmc.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.product.AttrGroup;
import com.zhuhang.ddmc.vo.product.AttrGroupQueryVo;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);
}
