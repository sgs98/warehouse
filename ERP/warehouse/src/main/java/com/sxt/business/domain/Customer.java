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
@TableName(value = "bus_customer")
public class Customer implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "customername")
    private String customername;

    @TableField(value = "zip")
    private String zip;

    @TableField(value = "address")
    private String address;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "connectionperson")
    private String connectionperson;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "bank")
    private String bank;

    @TableField(value = "account")
    private String account;

    @TableField(value = "email")
    private String email;

    @TableField(value = "fax")
    private String fax;

    @TableField(value = "available")
    private Integer available;

    private static final long serialVersionUID = 1L;
}