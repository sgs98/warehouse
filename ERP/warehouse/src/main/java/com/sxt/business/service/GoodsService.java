package com.sxt.business.service;

import com.sxt.business.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.GoodsVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/22
 */
public interface GoodsService extends IService<Goods>{

    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    DataGridView queryAllGoods(GoodsVo goodsVo);

    /**
     * 添加商品
     * @param goods
     * @return
     */
    Goods saveGoods(Goods goods);

    /**
     * 更新商品
     * @param goods
     * @return
     */
    Goods updateGoods(Goods goods);

    DataGridView getAllAvailableGoods();

    DataGridView getGoodsByProviderId(Integer providerid);
}
