package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddUserDto;
import com.guo.domain.dto.UpdateUserDto;
import com.guo.domain.dto.UserListDto;
import com.guo.domain.entity.User;
import com.guo.domain.vo.ChangeUserVo;
import com.guo.domain.vo.PageVo;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 17:56:35
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult<PageVo> pageUserList(Integer pageNum, Integer pageSize, UserListDto userListDto);

    ResponseResult addUser(AddUserDto addUserDto);

    ResponseResult deleteUser(String id);

    User getUser(Long id);

    ResponseResult updateUser(UpdateUserDto updateUserDto);

    ResponseResult changeStatus(ChangeUserVo changeUserVo);
}
