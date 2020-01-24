package com.sxt.business.service;

import com.sxt.business.domain.Inport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.InportVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/23
 */
public interface InportService extends IService<Inport>{

    /**
     * 查询
     * @param inportVo
     * @return
     */
    DataGridView queryAllInport(InportVo inportVo);

    /**
     * 添加
     * @param inport
     * @return
     */
    Inport saveInport(Inport inport);

    /**
     * 修改
     * @param inport
     * @return
     */
    Inport updateInport(Inport inport);
}
