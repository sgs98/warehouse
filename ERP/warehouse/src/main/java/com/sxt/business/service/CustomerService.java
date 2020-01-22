package com.sxt.business.service;

import com.sxt.business.domain.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.CustomerVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/22
 */
public interface CustomerService extends IService<Customer>{

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
        DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     *
     * @param customer
     */
        Customer saveCustomer(Customer customer);

    /**
     * 更新客户
     * @param customer
     * @return
     */
        Customer updateCustomer(Customer customer);
}
