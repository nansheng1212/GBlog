package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 05 10 06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {
    private String name;

    private String remark;
}
