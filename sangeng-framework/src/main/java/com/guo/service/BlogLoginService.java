package com.guo.service;

import com.guo.domain.ResponseResult;
import com.guo.domain.entity.User;

/**
 * @Author guo
 * @Date 2023 03 29 12 21
 **/
public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
