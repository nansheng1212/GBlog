package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.entity.ArticleTag;

import java.util.List;


/**
 * 文章标签关联表(ArticleTag)表服务接口
 *
 * @author makejava
 * @since 2023-04-05 16:44:34
 */
public interface ArticleTagService extends IService<ArticleTag> {

    List<Long> getTagList(Long id);
}
