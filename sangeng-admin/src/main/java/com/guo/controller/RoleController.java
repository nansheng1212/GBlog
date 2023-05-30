package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddRoleDto;
import com.guo.domain.dto.RoleListDto;
import com.guo.domain.dto.UpdateRoleDto;
import com.guo.domain.vo.ChangeRoleVo;
import com.guo.domain.vo.PageVo;
import com.guo.domain.vo.RoleMenuListVo;
import com.guo.domain.vo.RoleMenuVo;
import com.guo.service.RoleService;
import com.guo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 07 09 40
 **/
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, RoleListDto roleListDto){
        return roleService.pageRoleList(pageNum,pageSize,roleListDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleVo changeRoleVo) {
        return roleService.changeStatus(changeRoleVo);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeselect(){
        //查询menu 结果是tree的形式
        List<RoleMenuVo> menus = roleService.getTreeselect();
        //封装返回
        return ResponseResult.okResult(menus);
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getTreeselectById(@PathVariable Long id){
        //查询menu 结果是tree的形式
        List<RoleMenuVo> menus = roleService.getTreeselect();

        List list = roleService.getcheckedKeysList(id);
        //封装返回
        return ResponseResult.okResult(new RoleMenuListVo(menus,list));
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody AddRoleDto addRoleDto){
        return roleService.addRole(addRoleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable Long id){
        return roleService.getRole(id);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }

    @PutMapping
    public ResponseResult updateRole(@RequestBody UpdateRoleDto updateRoleDto){
        return roleService.updateRole(updateRoleDto);
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(){
        return roleService.listAllRole();
    }
}
