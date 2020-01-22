package com.sxt.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author song
 * @data 2020/1/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MenuVo extends BaseVo {
    Integer hasPermission;  //0不要权限
}
