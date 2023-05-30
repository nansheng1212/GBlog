package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.dto.MenuDto;
import com.guo.domain.vo.MenuListVo;
import com.guo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guo
 * @Date 2023 04 06 17 19
 **/

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult list(MenuDto menuDto){
        return menuService.list(menuDto);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody MenuListVo menuListVo){
        return menuService.addMenu(menuListVo);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable Long id){
        return menuService.getMenu(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody MenuListVo menuListVo){
        return menuService.updateMenu(menuListVo);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable Long menuId){
        return menuService.deleteMenu(menuId);
    }
}
