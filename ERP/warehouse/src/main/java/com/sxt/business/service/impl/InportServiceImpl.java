package com.sxt.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.business.domain.Goods;
import com.sxt.business.domain.Provider;
import com.sxt.business.service.GoodsService;
import com.sxt.business.service.ProviderService;
import com.sxt.business.vo.InportVo;
import com.sxt.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.business.mapper.InportMapper;
import com.sxt.business.domain.Inport;
import com.sxt.business.service.InportService;
/**
 *
 * @author song
 * @data 2020/1/23
 */
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService{
    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;
    @Override
    public DataGridView queryAllInport(InportVo inportVo) {
        IPage<Inport> page=new Page<>(inportVo.getPage(),inportVo.getLimit());

        QueryWrapper<Inport> qw=new QueryWrapper<>();

        qw.eq(inportVo.getGoodsid()!=null,"goodsid",inportVo.getGoodsid());
        qw.eq(inportVo.getProviderid()!=null,"providerid",inportVo.getProviderid());

        qw.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        qw.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());

        qw.orderByDesc("inporttime");

        this.inportMapper.selectPage(page,qw);
        List<Inport> records = page.getRecords();
        for (Inport record : records) {
            if(null!=record.getGoodsid()){
                Goods goods  = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!=record.getProviderid()){
                Provider provider = this.providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }

        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public Inport saveInport(Inport inport) {
        this.inportMapper.insert(inport);
        //更新库存
        Integer goodsid = inport.getGoodsid();
        Goods goods = this.goodsService.getById(goodsid);
        goods.setNumber(inport.getNumber()+goods.getNumber());
        this.goodsService.updateGoods(goods);
        return inport;
    }

    @Override
    public Inport updateInport(Inport inport) {
        Inport oldObj=this.inportMapper.selectById(inport.getId());
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());

        goods.setNumber(goods.getNumber()-oldObj.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        this.inportMapper.updateById(inport);
        return inport;
    }

    @Override
    public boolean removeById(Serializable id) {
        Inport inport = this.inportMapper.selectById(id);
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
