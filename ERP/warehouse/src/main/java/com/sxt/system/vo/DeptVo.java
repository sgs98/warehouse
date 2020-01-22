package com.sxt.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author song
 * @data 2020/1/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DeptVo extends BaseVo {
    private String title;
}
