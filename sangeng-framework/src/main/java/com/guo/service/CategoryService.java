package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.CategoryListDto;
import com.guo.domain.entity.Category;
import com.guo.domain.vo.AllCategoryVo;
import com.guo.domain.vo.CategoryVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-26 19:57:02
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();


    List<AllCategoryVo> getListAllCategory();

    ResponseResult pageCategoryList(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto);

    ResponseResult addCategory(CategoryVo categoryVo);

    ResponseResult getCategory(Long id);

    ResponseResult updateCategory(CategoryVo categoryVo);

    ResponseResult deleteCategory(Long id);
}
