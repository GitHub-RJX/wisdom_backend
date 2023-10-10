package com.rjx.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.rjx.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    //获取全部菜单
    List<Permission> getAllMenu();

    //给角色分配权限
    void saveRolePermission(String roleId, String[] permissionIds);

    //根据角色获取菜单
    List<Permission> selectAllMenu(String roleId);

    //递归删除菜单
    void removeChildById(String id);

    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    //获取全部菜单
    List<Permission> queryAllMenuGuli();

    //递归删除菜单
    void removeChildByIdGuli(String id);

    //给角色分配权限
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}
