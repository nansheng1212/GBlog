package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.MenuDto;
import com.guo.domain.entity.Menu;
import com.guo.domain.vo.MenuListVo;
import com.guo.domain.vo.MenuVo;
import com.guo.domain.vo.RoleMenuVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-04-03 21:13:37
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);


    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult list(MenuDto menuDto);

    ResponseResult addMenu(MenuListVo menuListVo);

    ResponseResult getMenu(Long id);

    ResponseResult updateMenu(MenuListVo menuListVo);

    ResponseResult deleteMenu(Long menuId);
}
