package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddRoleDto;
import com.guo.domain.dto.RoleListDto;
import com.guo.domain.dto.UpdateRoleDto;
import com.guo.domain.entity.Role;
import com.guo.domain.vo.*;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-04-03 20:33:11
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult<PageVo> pageRoleList(Integer pageNum, Integer pageSize, RoleListDto roleListDto);

    ResponseResult changeStatus(ChangeRoleVo changeRoleVo);

    List<RoleMenuVo> getTreeselect();

    ResponseResult addRole(AddRoleDto addRoleDto);

    ResponseResult getRole(Long id);

//根据id获得菜单树
/*    List<RoleMenuVo> getTreeselectById(Long id);*/

    List getcheckedKeysList(Long id);

    ResponseResult deleteRole(Long id);

    ResponseResult updateRole(UpdateRoleDto updateRoleDto);

    ResponseResult listAllRole();

    List<RoleVo> getRoles();
}
