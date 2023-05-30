package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 17:02:26
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
