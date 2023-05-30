package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.entity.UserRole;

import java.util.List;


/**
 * 用户和角色关联表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2023-04-09 17:44:04
 */
public interface UserRoleService extends IService<UserRole> {

    List<Long> getRoleIds(Long id);
}
