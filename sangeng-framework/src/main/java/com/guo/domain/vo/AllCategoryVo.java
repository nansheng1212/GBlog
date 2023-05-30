package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 05 16 27
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllCategoryVo {

    private Long id;

    //分类名
    private String name;

    //描述
    private String description;
}
