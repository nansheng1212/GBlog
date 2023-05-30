package com.guo.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author makejava
 * @since 2023-04-09 17:44:04
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole {
    //用户ID
    @TableId(type = IdType.INPUT)
    private Long userId;
    //角色ID@TableId
    private Long roleId;




}

