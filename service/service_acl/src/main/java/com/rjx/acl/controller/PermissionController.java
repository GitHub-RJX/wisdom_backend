package com.rjx.acl.controller;

import com.rjx.acl.entity.Permission;
import com.rjx.acl.service.PermissionService;
import com.rjx.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/list")
    public Result getAllMenu() {
        List<Permission> list = permissionService.getAllMenu();
        return Result.success(list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return Result.success();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionIds) {
        permissionService.saveRolePermission(roleId, permissionIds);
        return Result.success();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.success(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("/save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.success();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.success();
    }

}

