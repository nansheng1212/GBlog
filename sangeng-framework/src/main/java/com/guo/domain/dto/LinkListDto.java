package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 10 16 12
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkListDto {
    private String name;
    //审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
    private String status;

}
