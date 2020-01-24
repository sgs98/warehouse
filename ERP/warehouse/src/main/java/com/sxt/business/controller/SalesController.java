package com.sxt.business.controller;

import com.sxt.business.domain.Sales;
import com.sxt.business.service.SalesService;
import com.sxt.business.vo.SalesVo;
import com.sxt.system.common.ActiveUser;
import com.sxt.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author song
 * @data 2020/1/24
 */
@RestController
@RequestMapping("api/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;


    /**
     * 查询
     */
    @RequestMapping("loadAllSales")
    public Object loadAllSales(SalesVo salesVo){
        return this.salesService.queryAllSales(salesVo);
    }

    /**
     * 添加
     */
    @RequestMapping("addSales")
    public ResultObj addSales(Sales sales){
        try {
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            sales.setOperateperson(activeUser.getUser().getName());
            sales.setSalestime(new Date());
            this.salesService.saveSales(sales);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 修改
     */
    @RequestMapping("updateSales")
    public ResultObj updateSales(Sales sales){
        try {
            this.salesService.updateSales(sales);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除
     */
    @RequestMapping("deleteSales")
    public ResultObj deleteSales(Integer id){
        try {
            this.salesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
