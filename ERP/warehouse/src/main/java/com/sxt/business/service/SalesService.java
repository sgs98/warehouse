package com.sxt.business.service;

import com.sxt.business.domain.Sales;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.SalesVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/24
 */
public interface SalesService extends IService<Sales>{


    DataGridView queryAllSales(SalesVo salesVo);

    Sales saveSales(Sales sales);

    Sales updateSales(Sales sales);
}
