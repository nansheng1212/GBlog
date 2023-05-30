package com.guo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.Constants.SystemConstants;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.CategoryListDto;
import com.guo.domain.entity.Article;
import com.guo.domain.entity.Category;
import com.guo.domain.entity.Tag;
import com.guo.domain.vo.AllCategoryVo;
import com.guo.domain.vo.CategoryVo;
import com.guo.domain.vo.PageVo;
import com.guo.domain.vo.TagVo;
import com.guo.mapper.CategoryMapper;
import com.guo.service.ArticleService;
import com.guo.service.CategoryService;
import com.guo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 19:57:03
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表状态为已发布
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id并去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<AllCategoryVo> getListAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        List<Category> listAllCategory = list(queryWrapper);
        List<AllCategoryVo> categoryVos = BeanCopyUtils.copyBeanList(listAllCategory, AllCategoryVo.class);
        return categoryVos;
    }

    @Override
    public ResponseResult pageCategoryList(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto) {
        //分页查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(categoryListDto.getName()),Category::getName,categoryListDto.getName());
        queryWrapper.eq(StringUtils.hasText(categoryListDto.getStatus()),Category::getStatus,categoryListDto.getStatus());

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Page<Category> categoryPage = page(page, queryWrapper);
        //封装数据返回
        //封装成tagVo集合
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryPage.getRecords(), CategoryVo.class);
        PageVo pageVo = new PageVo(categoryVos,categoryPage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCategory(CategoryVo categoryVo) {
        Category category = BeanCopyUtils.copyBean(categoryVo, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategory(Long id) {
        Category category = getById(id);
        CategoryVo categoryVo = BeanCopyUtils.copyBean(category, CategoryVo.class);
        return ResponseResult.okResult(categoryVo);
    }

    @Override
    public ResponseResult updateCategory(CategoryVo categoryVo) {
        Category category = BeanCopyUtils.copyBean(categoryVo, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
