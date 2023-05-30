package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.domain.entity.RoleMenu;
import com.guo.mapper.RoleMenuMapper;
import com.guo.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-07 20:39:07
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<Long> getreMenuIds(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId,id);
        List<RoleMenu> list = getBaseMapper().selectList(queryWrapper);

        List<Long> menuList = new ArrayList<>();
        list.stream()
                .map(menuId->menuList.add(menuId.getMenuId()))
                .collect(Collectors.toList());

        return menuList;
    }
}
