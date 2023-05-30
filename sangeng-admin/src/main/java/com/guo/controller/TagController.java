package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.dto.AddTagDto;
import com.guo.domain.dto.TagListDto;
import com.guo.domain.dto.UpdateTagDto;
import com.guo.domain.entity.Tag;
import com.guo.domain.vo.PageVo;
import com.guo.domain.vo.TagVo;
import com.guo.service.TagService;
import com.guo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guo
 * @Date 2023 04 03 15 44
 **/

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult<TagVo> listAllTag(){
        return tagService.listAllTag();
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto){
        Tag tag = BeanCopyUtils.copyBean(addTagDto, Tag.class);
        return tagService.addTag(tag);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody UpdateTagDto updateTagDto){
        Tag tag = BeanCopyUtils.copyBean(updateTagDto, Tag.class);
        return tagService.updateTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable("id") Long id){
        return tagService.getTag(id);
    }
}
