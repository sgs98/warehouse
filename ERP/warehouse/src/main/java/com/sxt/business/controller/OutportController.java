package com.sxt.business.controller;

import com.sxt.business.domain.Outport;
import com.sxt.business.service.OutportService;
import com.sxt.business.vo.OutportVo;
import com.sxt.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @data 2020/1/24
 */
@RequestMapping("api/outport")
@RestController
public class OutportController {

    @Autowired
    private OutportService outportService;


    /**
     * 查询
     */
    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo) {
        return this.outportService.queryAllOutport(outportVo);
    }

    /**
     * 添加退货信息
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Outport outport) {
        try {
            this.outportService.saveOutport(outport);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}
