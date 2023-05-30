package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 04 04 11 38
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuListVo {
    private List<RoleMenuVo> menus;

    private List checkedKeys;

}
