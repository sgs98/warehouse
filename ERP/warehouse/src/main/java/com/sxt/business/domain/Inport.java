package com.sxt.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author song
 * @data 2020/1/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_inport")
public class Inport implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "inporttime")
    private Date inporttime;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "inportprice")
    private Double inportprice;

    @TableField(value = "providerid")
    private Integer providerid;
    @TableField(exist = false)
    private String providername;
    @TableField(value = "goodsid")
    private Integer goodsid;
    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;
    private static final long serialVersionUID = 1L;
}