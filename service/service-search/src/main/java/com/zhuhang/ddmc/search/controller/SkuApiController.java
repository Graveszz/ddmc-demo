package com.zhuhang.ddmc.search.controller;


import com.zhuhang.ddmc.common.result.Result;
import com.zhuhang.ddmc.model.search.SkuEs;
import com.zhuhang.ddmc.search.Service.SkuService;
import com.zhuhang.ddmc.vo.search.SkuEsQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品搜索列表接口
 * </p>
 */
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {

    @Autowired
    private SkuService skuService;

    //查询分类商品
//    @GetMapping("{page}/{limit}")
//    public Result listSku(@PathVariable Integer page,
//                          @PathVariable Integer limit,
//                          SkuEsQueryVo skuEsQueryVo) {
//        //创建pageable对象，0代表第一页
//        Pageable pageable = PageRequest.of(page-1,limit);
//        Page<SkuEs> pageModel = skuService.search(pageable,skuEsQueryVo);
//        return Result.success(pageModel);
//    }

    @ApiOperation(value = "上架商品")
    @GetMapping("inner/upperSku/{skuId}")
    public Result upperGoods(@PathVariable("skuId") Long skuId) {
        skuService.upperSku(skuId);
        return Result.success();
    }

    @ApiOperation(value = "下架商品")
    @GetMapping("inner/lowerSku/{skuId}")
    public Result lowerGoods(@PathVariable("skuId") Long skuId) {
        skuService.lowerSku(skuId);
        return Result.success();
    }

//    //获取爆款商品
//    @GetMapping("inner/findHotSkuList")
//    public List<SkuEs> findHotSkuList() {
//        return skuService.findHotSkuList();
//    }
//
//    //更新商品热度
//    @GetMapping("inner/incrHotScore/{skuId}")
//    public Boolean incrHotScore(@PathVariable("skuId") Long skuId) {
//        skuService.incrHotScore(skuId);
//        return true;
//    }
}
