package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddArticleDto;
import com.guo.domain.dto.ArticleListDto;
import com.guo.domain.entity.Article;
import com.guo.domain.vo.GetArticleVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult pageArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    ResponseResult getArticle(Long id);

    ResponseResult update(GetArticleVo getArticleVo);

    ResponseResult deleteArticle(Long id);
}
