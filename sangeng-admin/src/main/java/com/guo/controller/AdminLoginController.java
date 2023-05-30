package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.entity.LoginUser;
import com.guo.domain.entity.User;
import com.guo.domain.vo.AdminUserInfoVo;
import com.guo.domain.vo.MenuVo;
import com.guo.domain.vo.RoutesVo;
import com.guo.domain.vo.UserInfoVo;
import com.guo.enums.AppHttpCodeEnum;
import com.guo.exception.SystemException;
import com.guo.service.AdminLoginService;
import com.guo.service.MenuService;
import com.guo.service.RoleService;
import com.guo.utils.BeanCopyUtils;
import com.guo.utils.RedisCache;
import com.guo.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author guo
 * @Date 2023 03 29 12 15
 **/
@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKetList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKetList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutesVo> getRoutes() {
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<MenuVo> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装返回
        return ResponseResult.okResult(new RoutesVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return adminLoginService.logout();
    }
}
