package com.guo.service;

import com.guo.domain.ResponseResult;
import com.guo.domain.entity.User;

/**
 * @Author guo
 * @Date 2023 04 03 19 03
 **/
public interface AdminLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
