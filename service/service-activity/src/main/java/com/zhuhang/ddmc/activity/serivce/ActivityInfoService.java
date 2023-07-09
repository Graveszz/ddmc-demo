package com.zhuhang.ddmc.activity.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuhang.ddmc.model.activity.ActivityInfo;
import com.zhuhang.ddmc.model.activity.ActivityRule;
import com.zhuhang.ddmc.model.order.CartInfo;
import com.zhuhang.ddmc.model.product.SkuInfo;
import com.zhuhang.ddmc.vo.activity.ActivityRuleVo;
import com.zhuhang.ddmc.vo.order.CartInfoVo;
import com.zhuhang.ddmc.vo.order.OrderConfirmVo;

import java.util.List;
import java.util.Map;

public interface ActivityInfoService extends IService<ActivityInfo> {
    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    /**
     * 获取活动规则id
     * @param activityId
     * @return
     */
    Map<String, Object> findActivityRuleList(Long activityId);

    void saveActivityRule(ActivityRuleVo activityRuleVo);

    //根据关键字获取sku信息列表
    List<SkuInfo> findSkuInfoByKeyword(String keyword);

    //根据skuId列表获取促销信息
    Map<Long, List<String>> findActivity(List<Long> skuIdList);

    //根据skuID获取营销数据和优惠卷
    Map<String, Object> findActivityAndCoupon(Long skuId, Long userId);

    //根据skuId获取活动规则数据
    List<ActivityRule> findActivityRuleBySkuId(Long skuId);

    //获取购物车里面满足条件优惠卷和活动的信息
    OrderConfirmVo findCartActivityAndCoupon(List<CartInfo> cartInfoList, Long userId);

    //获取购物车对应规则数据
    List<CartInfoVo> findCartActivityList(List<CartInfo> cartInfoList);

    void saveActivitySku(SkuInfo skuInfo, ActivityInfo activityInfo);
}
