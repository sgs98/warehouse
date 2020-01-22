package com.sxt.business.controller;

import com.sxt.business.domain.Customer;
import com.sxt.business.service.CustomerService;
import com.sxt.business.vo.CustomerVo;
import com.sxt.system.common.Constant;
import com.sxt.system.common.ResultObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author song
 * @data 2020/1/22
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 加载所有客户
     * @param customerVo
     * @return
     */
    @RequestMapping("loadAllCustomer")
    public Object loadAllCustomer(CustomerVo customerVo){
        return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加客户
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(Customer customer){
        try {
            customer.setAvailable(Constant.AVAILABLE_TRUE);
            this.customerService.saveCustomer(customer);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除客户
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(Integer id){
        try {
            this.customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除客户
     */
    @RequestMapping("batchDeleteCustomer")
    public ResultObj bacthDeleteCustomer(Integer[] ids){
        try {
            if(null!=ids&&ids.length>0){
                List<Integer> idsList=new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.customerService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else{
                return new ResultObj(-1,"传入ID不能为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 更新客户
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(Customer customer){
        try {
            this.customerService.updateCustomer(customer);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
