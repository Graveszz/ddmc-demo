package com.zhuhang.ddmc.acl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuhang.ddmc.acl.service.RoleService;
import com.zhuhang.ddmc.common.result.Result;
import com.zhuhang.ddmc.model.acl.Role;
import com.zhuhang.ddmc.vo.acl.RoleQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author zhuhang
 * @since 2023-07-02
 */
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    //1.角色列表
    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result list(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            RoleQueryVo roleQueryVo) {
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> pageModel = roleService.selectPage(pageParam, roleQueryVo);
        return Result.success(pageModel);
    }


    //2.查询角色
    @ApiOperation(value = "根据id查询角色")
    @GetMapping("get/{id}")
    public Result selectById(
            @ApiParam(name = "id", value = "用户id", required = true)
            @PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.success(role);
    }


    //3.添加角色
    @ApiOperation(value = "添加角色")
    @PostMapping("save")
    public Result insertRole(
            @ApiParam(name = "role", value = "用户信息", required = true)
            @RequestBody Role role) {
        roleService.save(role);
        return Result.success(role);
    }

    //4.修改角色
    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result update(
            @ApiParam(name = "role", value = "用户信息", required = true)
            @RequestBody Role role) {
        roleService.updateById(role);
        return Result.success(role);
    }


    //5.根据id删除角色
    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "用户id", required = true)
            @PathVariable Long id) {
        roleService.removeById(id);
        return Result.success(id);
    }


    //6.批量删除角色
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("batchRemove")
    public Result remove(
            @ApiParam(name = "idList", value = "用户id列表", required = true)
            @RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return Result.success(idList);
    }

}

