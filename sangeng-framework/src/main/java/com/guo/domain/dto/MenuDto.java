package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 06 17 29
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    //菜单名称
    private String menuName;
    //菜单状态（0正常 1停用）
    private String status;
}
