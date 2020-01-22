package com.sxt.business.controller;

import com.sxt.business.domain.Provider;
import com.sxt.business.service.ProviderService;
import com.sxt.business.vo.ProviderVo;
import com.sxt.system.common.Constant;
import com.sxt.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author song
 * @data 2020/1/22
 */
@RestController
@RequestMapping("provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    /**
     * 加载所有供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("loadAllProvider")
    public Object loadAllProvider(ProviderVo providerVo){
        return this.providerService.queryAllProvider(providerVo);
    }

    /**
     * 添加供应商
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(Provider provider){
        try {
            provider.setAvailable(Constant.AVAILABLE_TRUE);
            this.providerService.saveProvider(provider);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除供应商
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id){
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除供应商
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj bacthDeleteProvider(Integer[] ids){
        try {
            if(null!=ids&&ids.length>0){
                List<Integer> idsList=new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.providerService.removeByIds(idsList);
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
     * 更新供应商
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(Provider provider){
        try {
            this.providerService.updateProvider(provider);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 查询所有可用的供应商，不分页
     */
    @GetMapping("getAllAvailableProvider")
    public Object getAllAvailableProvider(){
        return this.providerService.getAllAvailableProvider();
    }

}
