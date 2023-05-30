package com.guo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-03 20:33:11
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
