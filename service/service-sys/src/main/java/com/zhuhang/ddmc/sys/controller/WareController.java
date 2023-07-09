package com.zhuhang.ddmc.sys.controller;


import com.zhuhang.ddmc.common.result.Result;
import com.zhuhang.ddmc.sys.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-03
 */

@Api(value = "Ware管理", tags = "Ware管理")
@RestController
@RequestMapping("/admin/sys/ware")
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation(value = "获取全部仓库")
    @GetMapping("findAllList")
    public Result findAllList() {
        return Result.success(wareService.list());
    }

}

