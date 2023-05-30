package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guo
 * @Date 2023 03 26 20 14
 **/

@RestController
@RequestMapping("/category")
@Api(tags = "分类",description = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
