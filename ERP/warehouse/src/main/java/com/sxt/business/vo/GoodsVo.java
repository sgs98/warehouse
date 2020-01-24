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
public class GoodsVo extends BaseVo {
    private Integer providerid;
    private String goodsname;
    private String size;
    private String productcode;
    private String promitcode;
    private String description;
}
