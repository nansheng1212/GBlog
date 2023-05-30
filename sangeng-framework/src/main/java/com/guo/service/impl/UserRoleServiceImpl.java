package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.domain.entity.UserRole;
import com.guo.mapper.UserRoleMapper;
import com.guo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-04-09 17:44:04
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<Long> getRoleIds(Long id) {

        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,id);
        List<UserRole> list = list(queryWrapper);
        List<Long> roleList = new ArrayList<>();
        list.stream()
                .map(roleId->roleList.add(roleId.getRoleId()))
                .collect(Collectors.toList());
        return roleList;
    }
}
