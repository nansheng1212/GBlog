package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 07 09 57
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListDto {
    //角色名称
    private String roleName;
    //角色状态（0正常 1停用）
    private String status;
}
