package com.sxt.business.vo;

import com.sxt.system.vo.BaseVo;
import lombok.Data;

/**
 * @author song
 * @data 2020/1/22
 */
@Data
public class ProviderVo extends BaseVo {
    private String phone;
    private String connectionperson;
    private String providername;
}
