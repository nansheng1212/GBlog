package com.guo.controller;


import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddArticleDto;
import com.guo.domain.dto.ArticleListDto;
import com.guo.domain.vo.GetArticleVo;
import com.guo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guo
 * @Date 2023 04 05 16 38
 **/

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 增加文章
     * @param articleDto
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto articleDto){
        return articleService.add(articleDto);
    }

    /**
     * 更新文章
     * @param getArticleVo
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody GetArticleVo getArticleVo){
        return articleService.update(getArticleVo);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, ArticleListDto articleListDto){
        return articleService.pageArticleList(pageNum,pageSize,articleListDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable("id") Long id){
        return articleService.getArticle(id);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }
}
