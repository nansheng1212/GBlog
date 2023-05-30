package com.guo;

import com.guo.domain.entity.ArticleTag;
import com.guo.service.ArticleTagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author guo
 * @Date 2023 03 31 15 22
 **/
@SpringBootTest
public class OSSTest {

    @Autowired
    private ArticleTagService articleTagService;


    @Test
    public ArticleTag passwordEncoder(){

        ArticleTag byId = articleTagService.getById(1);

        return byId;
    }
}
