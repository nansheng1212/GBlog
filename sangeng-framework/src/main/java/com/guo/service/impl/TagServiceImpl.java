package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.TagListDto;
import com.guo.domain.entity.Tag;
import com.guo.domain.vo.AllTagVo;
import com.guo.domain.vo.PageVo;
import com.guo.domain.vo.TagVo;
import com.guo.enums.AppHttpCodeEnum;
import com.guo.exception.SystemException;
import com.guo.mapper.TagMapper;
import com.guo.service.TagService;
import com.guo.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-04-03 15:22:30
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Page<Tag> tagList = page(page, queryWrapper);
        //封装数据返回
        //封装成tagVo集合
        List<TagVo> tagVo = BeanCopyUtils.copyBeanList(tagList.getRecords(), TagVo.class);
        PageVo  pageVo = new PageVo(tagVo,tagList.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        //标签名不能为空
        if (!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.NAME_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        getBaseMapper().deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        getBaseMapper().selectById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(getBaseMapper().selectById(id), TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId,tag.getId());
        update(tag,queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<TagVo> listAllTag() {
        List<Tag> list = list();
        List<AllTagVo> tagVos = BeanCopyUtils.copyBeanList(list, AllTagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}
