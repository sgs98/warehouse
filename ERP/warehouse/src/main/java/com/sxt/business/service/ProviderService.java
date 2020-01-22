package com.sxt.business.service;

import com.sxt.business.domain.Provider;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.business.vo.ProviderVo;
import com.sxt.system.common.DataGridView;

/**
 *
 * @author song
 * @data 2020/1/22
 */
public interface ProviderService extends IService<Provider>{

    /**
     * 供应商查询
     * @param providerVo
     * @return
     */
    DataGridView queryAllProvider(ProviderVo providerVo);

    /**
     * 添加供应商
     * @param provider
     * @return
     */
    Provider saveProvider(Provider provider);

    /**
     * 修改供应商
     * @param provider
     * @return
     */
    Provider updateProvider(Provider provider);

    DataGridView getAllAvailableProvider();
}
