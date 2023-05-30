package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 05 16 30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTagVo {

    private Long id;
    //标签名
    private String name;
}
