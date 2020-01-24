package com.sxt.business.vo;

import com.sxt.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author song
 * @data 2020/1/22
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class InportVo extends BaseVo {

    private Integer providerid;
    private Integer goodsid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;


}
