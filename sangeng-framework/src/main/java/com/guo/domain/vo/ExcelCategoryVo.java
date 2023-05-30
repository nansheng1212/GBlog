package com.guo.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    //分类名
    private String name;

    @ExcelProperty("描述")
    //描述
    private String description;

    @ExcelProperty("状态")
    //状态0:正常,1禁用
    private String status;
}
