package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddUserDto;
import com.guo.domain.dto.UpdateUserDto;
import com.guo.domain.dto.UserListDto;
import com.guo.domain.entity.User;
import com.guo.domain.vo.*;
import com.guo.service.RoleService;
import com.guo.service.UserRoleService;
import com.guo.service.UserService;
import com.guo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 09 16 26
 **/

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, UserListDto userListDto){
        return userService.pageUserList(pageNum,pageSize,userListDto);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto){
        return userService.addUser(addUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult<UserVoList> getUserInfo(@PathVariable Long id){
        //获取用户关联角色列表
        List<Long> roleIds = userRoleService.getRoleIds(id);
        //获取所有角色信息
        List<RoleVo> roles = roleService.getRoles();
        //获取用户信息
        User reUser = userService.getUser(id);

        UserInfoVo user = BeanCopyUtils.copyBean(reUser, UserInfoVo.class);
        return ResponseResult.okResult(new UserVoList(roleIds,roles,user));
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody UpdateUserDto updateUserDto){
        return userService.updateUser(updateUserDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeUserVo changeUserVo){
        return userService.changeStatus(changeUserVo);
    }
}
