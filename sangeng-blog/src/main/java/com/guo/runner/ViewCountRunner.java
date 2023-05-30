package com.guo.runner;

import com.guo.domain.entity.Article;
import com.guo.mapper.ArticleMapper;
import com.guo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //存入redis
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}

/* articles.stream()
                .collect(Collectors.toMap(new Function<Article, Long>() {
                    @Override
                    public Long apply(Article article) {
                        return article.getId();
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));*/