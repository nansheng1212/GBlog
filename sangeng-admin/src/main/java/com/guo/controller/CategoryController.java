package com.guo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.CategoryListDto;
import com.guo.domain.dto.TagListDto;
import com.guo.domain.entity.Category;
import com.guo.domain.vo.AllCategoryVo;
import com.guo.domain.vo.CategoryVo;
import com.guo.domain.vo.ExcelCategoryVo;
import com.guo.enums.AppHttpCodeEnum;
import com.guo.service.CategoryService;
import com.guo.utils.BeanCopyUtils;
import com.guo.utils.WebUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author guo
 * @Date 2023 03 26 20 14
 **/

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult getListAllCategory(){
        List<AllCategoryVo> list = categoryService.getListAllCategory();
        return ResponseResult.okResult(list);
    }

    @GetMapping("/list")
    public ResponseResult pageCategoryList(Integer pageNum, Integer pageSize, CategoryListDto categoryListDto){
        return categoryService.pageCategoryList(pageNum,pageSize,categoryListDto);
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        //设置下载文件的请求头
        try {
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> Category = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(Category, ExcelCategoryVo.class);
            //写入Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody CategoryVo categoryVo){
        return categoryService.addCategory(categoryVo);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody CategoryVo categoryVo){
        return categoryService.updateCategory(categoryVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
