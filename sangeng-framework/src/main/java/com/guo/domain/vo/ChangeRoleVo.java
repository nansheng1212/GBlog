package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 07 10 04
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleVo {

    private Long roleId;

    //角色状态（0正常 1停用）
    private String status;
}
