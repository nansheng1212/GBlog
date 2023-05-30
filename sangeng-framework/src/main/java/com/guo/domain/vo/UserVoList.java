package com.guo.domain.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 09 19 51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoList {
    private List<Long> roleIds;
    private List<RoleVo> roles;
    private UserInfoVo user;
}
