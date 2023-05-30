package com.guo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 03 25 16 32
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private Long id;

    private String title;

    private Long viewCount;
}
