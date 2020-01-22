package com.sxt.system.service;

import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.system.vo.DeptVo;

/**
 *
 * @author song
 * @data 2020/1/19
 */
public interface DeptService extends IService<Dept>{

    /**
     * 查询所有部门
     * @param deptVo
     * @return
     */
    DataGridView queryAllDept(DeptVo deptVo);

    /**
     * 保存部门
     * @param dept
     */
    Dept saveDept(Dept dept);

    /**
     * 最大排序码
     * @return
     */
    Integer queryDeptMaxOrderNum();

    /**
     * 修改部门
     * @param dept
     * @return
     */
    Dept updateDept(Dept dept);

    /**
     * 检查当前ID的部门是否有子部门
     * @param id
     * @return
     */
    Integer queryDeptChildrenCountById(Integer id);
}
