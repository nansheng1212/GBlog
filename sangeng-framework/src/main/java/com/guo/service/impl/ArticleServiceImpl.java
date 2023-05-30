package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.Constants.SystemConstants;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddArticleDto;
import com.guo.domain.dto.ArticleListDto;
import com.guo.domain.entity.Article;
import com.guo.domain.entity.ArticleTag;
import com.guo.domain.entity.Category;
import com.guo.domain.vo.*;
import com.guo.mapper.ArticleMapper;
import com.guo.service.ArticleService;
import com.guo.service.ArticleTagService;
import com.guo.service.CategoryService;
import com.guo.utils.BeanCopyUtils;
import com.guo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author guo
 * @Date 2023 03 24 16 56
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //分页，一页10条
        Page<Article> page = new Page(1,10);

        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        //bean拷贝
        /*List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            articleVos.add(vo);
        }*/
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId就要 查询时要传入相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //articleId去查询articleName进行设置
        /*for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }*/
         articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结果
        List<ArticleDetailVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleDetailVo.class);
        //查询categoryName

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //封装查询结果
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应ID的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult pageArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {
        //分页查询
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(articleListDto.getTitle()),Article::getTitle,articleListDto.getTitle());
        queryWrapper.like(StringUtils.hasText(articleListDto.getSummary()),Article::getSummary,articleListDto.getSummary());

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Page<Article> articleList = page(page, queryWrapper);
        //封装数据返回
        //封装成tagVo集合
        List<ArticleVo> ArticleVo = BeanCopyUtils.copyBeanList(articleList.getRecords(), ArticleVo.class);
        PageVo  pageVo = new PageVo(ArticleVo,articleList.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticle(Long id) {
        Article article = getBaseMapper().selectById(id);
//        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(ArticleTag::getArticleId,id);
//        queryWrapper.select(ArticleTag::getTagId);
//        List<ArticleTag> list = articleTagService.list(queryWrapper);
//        List<Long> longList = new ArrayList<>();
//        for (ArticleTag articleTag : list) {
//            Long tagId = articleTag.getTagId();
//            longList.add(tagId);
//        }
        List<Long> tagList = articleTagService.getTagList(article.getId());
        GetArticleVo getArticleVo = BeanCopyUtils.copyBean(article, GetArticleVo.class);
        getArticleVo.setTags(tagList);
        return ResponseResult.okResult(getArticleVo);
    }

    @Override
    public ResponseResult update(GetArticleVo getArticleVo) {
        Article article = BeanCopyUtils.copyBean(getArticleVo, Article.class);
        updateById(article);
        //更新前的tag集合
        List<Long> tagList = articleTagService.getTagList(getArticleVo.getId());
        //getArticleVo.getTags()更新后的tag集合
        List<ArticleTag> articleTags = getArticleVo.getTags().stream()
                .filter(tagId->!tagList.contains(tagId))
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
                if (articleTags.size()>0){
                    articleTagService.saveBatch(articleTags);
                }else if (articleTags.size()==0){
                    List<ArticleTag> articleTags2 = tagList.stream()
                            .filter(tagId->!getArticleVo.getTags().contains(tagId))
                            .map(tagId -> new ArticleTag(article.getId(), tagId))
                            .collect(Collectors.toList());
                    for (ArticleTag articleTag : articleTags2) {
                        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
                        queryWrapper.eq(ArticleTag::getArticleId,getArticleVo.getId());
                        queryWrapper.eq(ArticleTag::getTagId,articleTag.getTagId());
                        articleTagService.remove(queryWrapper);
                    }
                }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long id) {
        /**
         * 删除文章
         */
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, id);
        remove(queryWrapper);

        /**
         * 接触文章对应的标签关系
         */
        //获取tag集合
        LambdaQueryWrapper<ArticleTag> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ArticleTag::getArticleId,id);
        List<ArticleTag> list = articleTagService.list(queryWrapper1);
        for (ArticleTag articleTag : list) {
            LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ArticleTag::getArticleId,articleTag.getArticleId());
            wrapper.eq(ArticleTag::getTagId,articleTag.getTagId());
            articleTagService.remove(wrapper);
        }
        return ResponseResult.okResult();
    }
}