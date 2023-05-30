package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.domain.entity.ArticleTag;
import com.guo.mapper.ArticleTagMapper;
import com.guo.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-04-05 16:44:34
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public List<Long> getTagList(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,id);
        queryWrapper.select(ArticleTag::getTagId);
        List<ArticleTag> list = list(queryWrapper);
        List<Long> longList = new ArrayList<>();
        for (ArticleTag articleTag : list) {
            Long tagId = articleTag.getTagId();
            longList.add(tagId);
        }
        return longList;
    }
}
