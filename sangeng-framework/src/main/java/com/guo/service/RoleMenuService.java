package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.entity.RoleMenu;

import java.util.List;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2023-04-07 20:39:07
 */
public interface RoleMenuService extends IService<RoleMenu> {

    List<Long> getreMenuIds(Long id);
}
