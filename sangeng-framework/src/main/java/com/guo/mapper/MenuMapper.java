package com.guo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.domain.entity.Menu;
import com.guo.domain.vo.MenuVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-03 21:13:36
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<MenuVo> selectAllRouter();

    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);
}
