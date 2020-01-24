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
@TableName(value = "bus_outport")
public class Outport implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "providerid")
    private Integer providerid;

    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "outporttime")
    private Date outporttime;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "outportprice")
    private Double outportprice;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "goodsid")
    private Integer goodsid;

    @TableField(exist = false)
    private String goodsname;

    @TableField(value = "inportid")
    private Integer inportid;
    @TableField(exist = false)
    private String providername;

    @TableField(exist = false)
    private String size;
    private static final long serialVersionUID = 1L;
}