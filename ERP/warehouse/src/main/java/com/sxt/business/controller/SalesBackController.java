package com.sxt.business.controller;

import com.sxt.business.domain.Salesback;
import com.sxt.business.service.SalesbackService;
import com.sxt.business.vo.SalesbackVo;
import com.sxt.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @data 2020/1/24
 */
@RequestMapping("api/salesback")
@RestController
public class SalesBackController {

    @Autowired
    private SalesbackService salesbackService;


    /**
     * 查询
     */
    @RequestMapping("loadAllSalesback")
    public Object loadAllSalesback(SalesbackVo salesbackVo){
        return this.salesbackService.queryAllSalesback(salesbackVo);
    }

    /**
     * 添加退货信息
     */
    @RequestMapping("addSalesback")
    public ResultObj addSalesback(Salesback salesback){
        try {
            this.salesbackService.saveSalesback(salesback);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}
