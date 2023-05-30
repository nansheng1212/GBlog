package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.TagListDto;
import com.guo.domain.entity.Tag;
import com.guo.domain.vo.PageVo;
import com.guo.domain.vo.TagVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-04-03 15:22:29
 */
public interface TagService extends IService<Tag> {


    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);

    ResponseResult<TagVo> listAllTag();
}
