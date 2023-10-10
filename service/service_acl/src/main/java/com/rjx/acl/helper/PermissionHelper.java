package com.rjx.acl.helper;

import com.rjx.acl.entity.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    public static List<Permission> build(List<Permission> treeNodes) {
        List<Permission> permissionList = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                permissionList.add(findChildren(treeNode, treeNodes));
            }
        }
        return permissionList;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<>());

        for (Permission permission : treeNodes) {
            if (treeNode.getId().equals(permission.getPid())) {
                int level = treeNode.getLevel() + 1;
                permission.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(permission, treeNodes));
            }
        }
        return treeNode;
    }
}
