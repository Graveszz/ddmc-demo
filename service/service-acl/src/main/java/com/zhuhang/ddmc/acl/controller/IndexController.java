package com.zhuhang.ddmc.acl.controller;

import com.zhuhang.ddmc.acl.service.AdminService;
import com.zhuhang.ddmc.common.result.Result;
import com.zhuhang.ddmc.common.result.ResultCodeEnum;
import com.zhuhang.ddmc.model.acl.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Autowired
    AdminService adminService;

    /**
     * 1、请求登陆的login
     */
    @ApiOperation("登录")
    @PostMapping("login")
//    public Result login(@PathVariable String username, @PathVariable String password) {
      public Result login(){
//        Admin admin=adminService.getByUsername();
//        if (!admin.getPassword().equals(password)){
//            return Result.fail();
//        }
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.success(map);
    }

    /**
     * 2 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhuhang111");
        map.put("password","123456");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }

    /**
     * 3 退出
     */
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout(){
        return Result.success(null);
    }
}
