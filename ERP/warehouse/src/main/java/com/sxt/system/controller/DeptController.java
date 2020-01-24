package com.sxt.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxt.system.common.Constant;
import com.sxt.system.common.DataGridView;
import com.sxt.system.common.ResultObj;
import com.sxt.system.domain.Dept;
import com.sxt.system.service.DeptService;
import com.sxt.system.vo.DeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author song
 * @data 2020/1/19
 */
@RequestMapping("api/dept")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /***
     * 查询部门
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo){
        return this.deptService.queryAllDept(deptVo);
    }

    /**
     * 查询部门最大的排序码
     */

    @GetMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum(){
        Integer maxValue=this.deptService.queryDeptMaxOrderNum();
        return new DataGridView(maxValue+1);
    }
    /**
     * 添加部门
     */
    @PostMapping("addDept")
    @RequiresPermissions("dept:add")
    public ResultObj addDept(Dept dept){
        try {
            dept.setSpread(Constant.SPREAD_FALSE);
            dept.setAvailable(Constant.AVAILABLE_TRUE);
            Dept d=this.deptService.saveDept(dept);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改部门
     */
    @RequiresPermissions("dept:update")
    @PostMapping("updateDept")
    public ResultObj updateDept(Dept dept){
        try {
            Dept d=this.deptService.updateDept(dept);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     *装载数据表格查询部门
     */
    @GetMapping("getDeptById")
    public Object getDeptById(Integer id){
        return new DataGridView(this.deptService.getById(id));
    }

    /*
     * 检查当前ID的部门是否有子部门
     * */
    @GetMapping("getDeptHasChildrenCountById")
    public Object checkDeptHasChildrenNode(Integer id){
        Integer count=this.deptService.queryDeptChildrenCountById(id);
        return new DataGridView(count);
    }
    /**
     * 删除部门
     */
    @RequiresPermissions("dept:delete")
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(Integer id){
        try {
            this.deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
