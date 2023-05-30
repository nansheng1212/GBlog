package com.guo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author guo
 * @Date 2023 04 05 20 26
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListDto {

    //标题
    private String title;
    //文章摘要
    private String summary;
}
