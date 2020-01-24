package com.sxt.business.vo;

import com.sxt.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author song
 * @data 2020/1/22
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerVo extends BaseVo {
    private String customername;
    private String phone;
    private String connectionperson;
}
