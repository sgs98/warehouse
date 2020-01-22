package com.sxt.business.controller;

import com.sxt.business.domain.Goods;
import com.sxt.business.service.GoodsService;
import com.sxt.business.vo.GoodsVo;
import com.sxt.system.common.Constant;
import com.sxt.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author song
 * @data 2020/1/22
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 加载所有商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllGoods")
    public Object loadAllGoods(GoodsVo goodsVo){
        return this.goodsService.queryAllGoods(goodsVo);
    }

    /**
     * 添加商品
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(Goods goods){
        try {
            goods.setAvailable(Constant.AVAILABLE_TRUE);
            this.goodsService.saveGoods(goods);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除商品
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id){
        try {
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除商品
     */
    @RequestMapping("batchDeleteGoods")
    public ResultObj bacthDeleteGoods(Integer[] ids){
        try {
            if(null!=ids&&ids.length>0){
                List<Integer> idsList=new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.goodsService.removeByIds(idsList);
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
     * 更新商品
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Goods goods){
        try {
            this.goodsService.updateGoods(goods);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
