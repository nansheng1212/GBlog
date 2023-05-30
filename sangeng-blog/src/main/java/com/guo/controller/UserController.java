package com.guo.controller;

import com.guo.annotation.SystemLog;
import com.guo.domain.ResponseResult;
import com.guo.domain.entity.User;
import com.guo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guo
 * @Date 2023 03 31 10 20
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户",description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(BusinessName = "查看个人信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(BusinessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(BusinessName = "用户注册")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
