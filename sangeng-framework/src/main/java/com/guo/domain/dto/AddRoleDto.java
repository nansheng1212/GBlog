package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 07 10 04
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleDto {

    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;

    private List<Long> menuIds;

    //备注
    private String remark;
}
