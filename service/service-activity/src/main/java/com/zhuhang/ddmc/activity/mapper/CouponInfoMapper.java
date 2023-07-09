package com.zhuhang.ddmc.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhuhang.ddmc.model.activity.CouponInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author zhuhang
 * @since 2023-06-16
 */
@Repository
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    //1 根据userId获取用户全部优惠卷
    List<CouponInfo> selectCartCouponInfoList(@Param("userId") Long userId);

    //2 根据skuId+userId查询优惠卷信息
    List<CouponInfo> selectCouponInfoList(@Param("skuId") Long id,
                                          @Param("categoryId") Long categoryId,
                                          @Param("userId") Long userId);

}
