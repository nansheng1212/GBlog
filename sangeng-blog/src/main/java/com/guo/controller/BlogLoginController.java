package com.guo.controller;

import com.guo.annotation.SystemLog;
import com.guo.domain.ResponseResult;
import com.guo.domain.entity.User;
import com.guo.enums.AppHttpCodeEnum;
import com.guo.exception.SystemException;
import com.guo.service.BlogLoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guo
 * @Date 2023 03 29 12 15
 **/
@RestController
@Api(tags = "登录登出",description = "登录登出相关接口")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    @SystemLog(BusinessName = "用户登录")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(BusinessName = "用户登出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
