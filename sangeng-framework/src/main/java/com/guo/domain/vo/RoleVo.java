package com.guo.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author guo
 * @Date 2023 04 07 10 04
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

    private Long id;

    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;

    private Long createBy;
    private Date createTime;

    private String remark;

    //删除标志（0代表存在 1代表删除）
    private String delFlag;

    //更新者
    private Long updateBy;
}
