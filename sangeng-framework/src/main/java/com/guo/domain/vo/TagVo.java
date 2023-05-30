package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 05 10 25
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    private Long id;
    //标签名
    private String name;
    //备注
    private String remark;
}
