package com.sxt.business.service;

import com.sxt.business.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.OutportVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/23
 */
public interface OutportService extends IService<Outport>{


        DataGridView queryAllOutport(OutportVo outportVo);

        Outport saveOutport(Outport outport);
}
