package com.sxt.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author song
 * @data 2020/1/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class Menu implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级菜单ID
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 类型[topmenu/leftmenu/permission]
     */
    @TableField(value = "type")
    private String type;

    /**
     * topmenu:system/business
permission:menu:addMenu

     */
    @TableField(value = "typecode")
    private String typecode;

    /**
     * 名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 连接地址
     */
    @TableField(value = "href")
    private String href;

    @TableField(value = "target")
    private String target;

    /**
     * 是否展开
     */
    @TableField(value = "spread")
    private Integer spread;

    /**
     * 排序码
     */
    @TableField(value = "ordernum")
    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    @TableField(value = "available")
    private Integer available;
}