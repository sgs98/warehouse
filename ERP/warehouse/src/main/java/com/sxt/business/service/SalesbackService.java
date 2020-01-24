package com.sxt.business.service;

import com.sxt.business.domain.Salesback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.SalesbackVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/24
 */
public interface SalesbackService extends IService<Salesback>{


        DataGridView queryAllSalesback(SalesbackVo salesbackVo);

        Salesback saveSalesback(Salesback salesback);
}
