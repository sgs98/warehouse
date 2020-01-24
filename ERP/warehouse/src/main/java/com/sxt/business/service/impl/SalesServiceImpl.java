package com.sxt.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.business.domain.Customer;
import com.sxt.business.domain.Goods;
import com.sxt.business.service.CustomerService;
import com.sxt.business.service.GoodsService;
import com.sxt.business.vo.SalesVo;
import com.sxt.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.business.mapper.SalesMapper;
import com.sxt.business.domain.Sales;
import com.sxt.business.service.SalesService;
/**
 *
 * @author song
 * @data 2020/1/24
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService{
    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public DataGridView queryAllSales(SalesVo salesVo) {
        IPage<Sales> page=new Page<>(salesVo.getPage(),salesVo.getLimit());

        QueryWrapper<Sales> qw=new QueryWrapper<>();

        qw.eq(salesVo.getGoodsid()!=null,"goodsid",salesVo.getGoodsid());
        qw.eq(salesVo.getCustomerid()!=null,"customerid",salesVo.getCustomerid());

        qw.ge(salesVo.getStartTime()!=null,"salestime",salesVo.getStartTime());
        qw.le(salesVo.getEndTime()!=null,"salestime",salesVo.getEndTime());

        qw.orderByDesc("salestime");

        this.salesMapper.selectPage(page,qw);
        List<Sales> records = page.getRecords();
        for (Sales record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getCustomerid()){
                Customer customer = this.customerService.getById(record.getCustomerid());
                record.setCustomername(customer.getCustomername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public Sales saveSales(Sales sales) {
        this.salesMapper.insert(sales);

        Integer goodsId = sales.getGoodsid();
        Goods goods = this.goodsService.getById(goodsId);

        goods.setNumber(goods.getNumber()-sales.getNumber());
        this.goodsService.updateGoods(goods);
        return sales;
    }

    @Override
    public Sales updateSales(Sales sales) {
        Sales oldObj = this.salesMapper.selectById(sales.getId());

        Goods goods = this.goodsService.getById(oldObj.getGoodsid());

        goods.setNumber(goods.getNumber()+oldObj.getNumber()-sales.getNumber());
        this.goodsService.updateGoods(goods);
        this.salesMapper.updateById(sales);
        return sales;
    }

    @Override
    public boolean removeById(Serializable id) {
        Sales sales = this.salesMapper.selectById(id);
        Goods goods = this.goodsService.getById(sales.getGoodsid());
        goods.setNumber(goods.getNumber()+sales.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
