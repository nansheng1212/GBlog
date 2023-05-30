package com.guo.service.impl;

import com.guo.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 05 19 57
 **/
@Service("ps")
public class PermissionService {

    /**
     * //判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission){
        //如果是超级管理员直接返回true
        if (SecurityUtils.isAdmin()){
            return true;
        }
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        //否则 获取用户所具有的权限列表判断是否存在permission
        return permissions.contains(permission);
    }
}
