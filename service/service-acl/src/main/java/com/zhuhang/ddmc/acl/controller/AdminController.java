package com.zhuhang.ddmc.acl.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.zhuhang.ddmc.MD5;
import com.zhuhang.ddmc.acl.service.AdminService;
import com.zhuhang.ddmc.acl.service.RoleService;
import com.zhuhang.ddmc.common.result.Result;
import com.zhuhang.ddmc.model.acl.Admin;
import com.zhuhang.ddmc.vo.acl.AdminQueryVo;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
@Api("用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@Slf4j
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    //1、 获取用户分页列表
    @ApiOperation("获取用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result list(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            AdminQueryVo adminQueryVo) {
        Page<Admin> adminPage = new Page<>(page, limit);
        IPage<Admin> pageModel = adminService.selectPage(adminPage, adminQueryVo);
        return Result.success(pageModel);
    }

    //2. 保存用户信息
    @ApiOperation("保存用户信息")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        boolean isSuccess = adminService.save(admin);
        if (isSuccess) {
            return Result.success();
        }
        return Result.fail();
    }

    //3. 根据id查询用户信息
    @ApiOperation("根据id查询用户信息")
    @PostMapping("get/{id}")
    public Result get(Long id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    //4.修改用户
    @ApiOperation(value = "修改用户")
    @PutMapping("update")
    public Result update(
            @ApiParam(name = "admin", value = "用户信息", required = true)
            @RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.success(admin);
    }

    //5.根据id删除用户
    @ApiOperation(value = "根据id删除用户")
    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "用户id", required = true)
            @PathVariable Long id) {
        adminService.removeById(id);
        return Result.success(id);
    }


    //6.批量删除用户
    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("batchRemove")
    public Result remove(
            @ApiParam(name = "idList", value = "用户id列表", required = true)
            @RequestBody List<Long> idList) {
        adminService.removeByIds(idList);
        return Result.success(idList);
    }
}

