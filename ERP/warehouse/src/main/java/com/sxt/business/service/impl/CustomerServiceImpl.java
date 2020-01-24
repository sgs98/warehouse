package com.sxt.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.business.vo.CustomerVo;
import com.sxt.system.common.Constant;
import com.sxt.system.common.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.business.domain.Customer;
import com.sxt.business.mapper.CustomerMapper;
import com.sxt.business.service.CustomerService;
/**
 *
 * @author song
 * @data 2020/1/22
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page=new Page<>(customerVo.getPage(),customerVo.getLimit());
        QueryWrapper<Customer> qw=new QueryWrapper<>();
        qw.eq(customerVo.getAvailable()!=null,"available",customerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        qw.like(StringUtils.isNotBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        if(StringUtils.isNotBlank(customerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Customer>>() {
                @Override
                public void accept(QueryWrapper<Customer> customerQueryWrapper) {
                    customerQueryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(customerVo.getPhone()),"telephone",customerVo.getPhone());
                }
            });
        }
        this.customerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @CacheEvict(cacheNames="com.sxt.business.service.impl.CustomerServiceImpl",key="#result.id")
    @Override
    public Customer saveCustomer(Customer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }

    /**
     * 更新客户
     * @param customer
     * @return
     */
    @CachePut(cacheNames="com.sxt.business.service.impl.CustomerServiceImpl",key="#result.id")
    @Override
    public Customer updateCustomer(Customer customer) {
        this.customerMapper.updateById(customer);
        return this.customerMapper.selectById(customer.getId());
    }

    @Override
    public DataGridView getAllAvailableCustomer() {
        QueryWrapper<Customer> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        return new DataGridView(this.customerMapper.selectList(qw));
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @CacheEvict(cacheNames="com.sxt.business.service.impl.CustomerServiceImpl",key="#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
