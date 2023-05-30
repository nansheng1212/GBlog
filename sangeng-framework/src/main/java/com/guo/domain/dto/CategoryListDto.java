package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author guo
 * @Date 2023 04 10 15 06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto {
    //分类名
    private String name;
    //状态0:正常,1禁用
    private String status;
}
