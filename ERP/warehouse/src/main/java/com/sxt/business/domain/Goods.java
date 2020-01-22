package com.sxt.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author song
 * @data 2020/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_goods")
public class Goods implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "goodsname")
    private String goodsname;

    @TableField(value = "produceplace")
    private String produceplace;

    @TableField(value = "size")
    private String size;

    @TableField(value = "goodspackage")
    private String goodspackage;

    @TableField(value = "productcode")
    private String productcode;

    @TableField(value = "promitcode")
    private String promitcode;

    @TableField(value = "description")
    private String description;

    @TableField(value = "price")
    private Double price;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "dangernum")
    private Integer dangernum;

    @TableField(value = "goodsimg")
    private String goodsimg;

    @TableField(value = "available")
    private Integer available;

    @TableField(value = "providerid")
    private Integer providerid;

    @TableField(exist = false)
    private String providername;
    private static final long serialVersionUID = 1L;
}