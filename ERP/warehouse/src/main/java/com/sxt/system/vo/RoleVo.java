package com.sxt.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author song
 * @data 2020/1/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends BaseVo {
    private Integer userId;
    private String name;
    private String remark;
}
