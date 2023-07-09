package com.zhuhang.ddmc.search.Service;

import com.zhuhang.ddmc.model.search.SkuEs;
import com.zhuhang.ddmc.vo.search.SkuEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkuService {

//    //查询分类商品
//    Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo);

    /**
     * 上架商品列表
     * @param skuId
     */
    void upperSku(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerSku(Long skuId);

    //获取爆款商品
//    List<SkuEs> findHotSkuList();
//
//
//    //更新商品热度
//    void incrHotScore(Long skuId);
}
