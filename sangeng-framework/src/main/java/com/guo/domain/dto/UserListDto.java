package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 09 16 30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    //用户名
    private String userName;
    //账号状态（0正常 1停用）
    private String status;
    //手机号
    private String phonenumber;
}
