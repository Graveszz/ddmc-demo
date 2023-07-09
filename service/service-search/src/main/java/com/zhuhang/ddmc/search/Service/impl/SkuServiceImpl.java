package com.zhuhang.ddmc.search.Service.impl;

import com.alibaba.fastjson.JSON;
//import com.zhuhang.ddmc.client.ActivityFeignClient;
import com.zhuhang.ddmc.client.ProductFeignClient;
//import com.zhuhang.ddmc.common.auth.AuthContextHolder;
import com.zhuhang.ddmc.enums.SkuType;
import com.zhuhang.ddmc.model.product.Category;
import com.zhuhang.ddmc.model.product.SkuInfo;
import com.zhuhang.ddmc.model.search.SkuEs;
import com.zhuhang.ddmc.search.Service.SkuService;
import com.zhuhang.ddmc.search.repository.SkuRepository;
import com.zhuhang.ddmc.vo.search.SkuEsQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    private SkuRepository skuEsRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

//    @Autowired
//    private ActivityFeignClient activityFeignClient;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上架商品列表
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        log.info("upperSku："+skuId);
        SkuEs skuEs = new SkuEs();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if(null == skuInfo) return;

        // 查询分类
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if(skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品

        }
        SkuEs save = skuEsRepository.save(skuEs);
        log.info("upperSku："+JSON.toJSONString(save));
    }

    /**
     * 下架商品列表
     * @param skuId
     */
    @Override
    public void lowerSku(Long skuId) {
        this.skuEsRepository.deleteById(skuId);
    }



//    //更新商品热度
//    @Override
//    public void incrHotScore(Long skuId) {
//        String key = "hotScore";
//        //redis保存数据，每次+1
//        Double hotScore = redisTemplate.opsForZSet().incrementScore(key, "skuId:" + skuId, 1);
//        //规则
//        if(hotScore%10==0) {
//            //更新es
//            Optional<SkuEs> optional = skuEsRepository.findById(skuId);
//            SkuEs skuEs = optional.get();
//            skuEs.setHotScore(Math.round(hotScore));
//            skuEsRepository.save(skuEs);
//        }
//    }
//
//    //获取爆款商品
//    @Override
//    public List<SkuEs> findHotSkuList() {
//        //find  read  get开头
//        //关联条件关键字
//        // 0代表第一页
//        Pageable pageable = PageRequest.of(0,10);
//        Page<SkuEs> pageModel = skuEsRepository.findByOrderByHotScoreDesc(pageable);
//        List<SkuEs> skuEsList = pageModel.getContent();
//        return skuEsList;
//    }
//
//    //查询分类商品
//    @Override
//    public Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo) {
//        //1 向SkuEsQueryVo设置wareId，当前登录用户的仓库id
//        skuEsQueryVo.setWareId(AuthContextHolder.getWareId());
//
//        Page<SkuEs> pageModel = null;
//        //2 调用SkuRepository方法，根据springData命名规则定义方法，进行条件查询
//        //// 判断keyword是否为空，如果为空 ，根据仓库id + 分类id查询
//        String keyword = skuEsQueryVo.getKeyword();
//        if(StringUtils.isEmpty(keyword)) {
//            pageModel =
//                    skuEsRepository
//                            .findByCategoryIdAndWareId(
//                                    skuEsQueryVo.getCategoryId(),
//                                    skuEsQueryVo.getWareId(),
//                                    pageable);
//        } else {
//            ///如果keyword不为空根据仓库id + keyword进行查询
//            pageModel = skuEsRepository
//                    .findByKeywordAndWareId(
//                            skuEsQueryVo.getKeyword(),
//                            skuEsQueryVo.getWareId(),
//                            pageable);
//        }
//
//        //3 查询商品参加优惠活动
//        List<SkuEs> skuEsList = pageModel.getContent();
//
//        if(!CollectionUtils.isEmpty(skuEsList)) {
//            //遍历skuEsList，得到所有skuId
//            List<Long> skuIdList =
//                    skuEsList.stream()
//                            .map(item -> item.getId())
//                            .collect(Collectors.toList());
//            //根据skuId列表远程调用，调用service-activity里面的接口得到数据
//            //返回Map<Long,List<String>>
//            //// map集合key就是skuId值，Long类型
//            //// map集合value是List集合，sku参与活动里面多个规则名称列表
//            ///// 一个商品参加一个活动，一个活动里面可以有多个规则
//            ////// 比如有活动：中秋节满减活动
//            ////// 一个活动可以有多个规则：
//            ////// 中秋节满减活动有两个规则：满20元减1元，满58元减5元
//            Map<Long,List<String>> skuIdToRuleListMap =
//                    activityFeignClient.findActivity(skuIdList);//远程调用
//            //封装获取数据到skuEs里面 ruleList属性里面
//            if(skuIdToRuleListMap != null) {
//                skuEsList.forEach(skuEs -> {
//                    skuEs.setRuleList(skuIdToRuleListMap.get(skuEs.getId()));
//                });
//            }
//        }
//        return pageModel;
//    }
}