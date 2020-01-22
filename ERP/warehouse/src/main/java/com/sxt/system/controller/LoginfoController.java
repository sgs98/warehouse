package com.sxt.system.controller;

import com.sxt.system.common.ResultObj;
import com.sxt.system.service.LoginfoService;
import com.sxt.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author song
 * @data 2020/1/18
 */
@RequestMapping("loginfo")
@RestController
public class LoginfoController {
    @Autowired
    private LoginfoService loginfoService;

    /**
     * 查询登陆日志
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo){
      return this.loginfoService.queryAllLoginfo(loginfoVo);
    }

    /**
     * 日志删除
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 日志批量删除
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            if(null!=ids&&ids.length>0){
                List<Integer> idsList=new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.loginfoService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else{
                return new ResultObj(-1,"传入ID不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
