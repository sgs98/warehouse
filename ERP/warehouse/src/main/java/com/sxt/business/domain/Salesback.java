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
 * @data 2020/1/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_salesback")
public class Salesback implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "customerid")
    private Integer customerid;
    @TableField(exist = false)
    private String customername;
    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "salesbacktime")
    private Date salesbacktime;

    @TableField(value = "salebackprice")
    private Double salebackprice;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "salesid")
    private Integer salesid;

    @TableField(value = "goodsid")
    private Integer goodsid;
    @TableField(exist = false)
    private String goodsname;
    @TableField(exist = false)
    private String size;
    private static final long serialVersionUID = 1L;
}