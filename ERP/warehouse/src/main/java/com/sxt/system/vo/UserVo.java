package com.sxt.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author song
 * @data 2020/1/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends BaseVo {
    private String name;
    private Integer deptid;
    private String address;
    private String remark;
}
