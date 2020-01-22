package com.sxt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxt.system.common.DataGridView;
import com.sxt.system.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.system.mapper.DeptMapper;
import com.sxt.system.domain.Dept;
import com.sxt.system.service.DeptService;
/**
 *
 * @author song
 * @data 2020/1/19
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw=new QueryWrapper<>();
        qw.orderByAsc("ordernum");
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        List<Dept> depts = this.deptMapper.selectList(qw);
        return new DataGridView(Long.valueOf(depts.size()),depts);
    }
    @CachePut(cacheNames = "com.sxt.system.service.impl.DeptServiceImpl",key = "#result.id")
    @Override
    public Dept saveDept(Dept dept) {
        this.deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return this.deptMapper.queryDeptMaxOrderNum();
    }
    @CachePut(cacheNames = "com.sxt.system.service.impl.DeptServiceImpl",key = "#result.id")
    @Override
    public Dept updateDept(Dept dept) {
        this.deptMapper.updateById(dept);
        return dept;
    }

    @Override
    public Integer queryDeptChildrenCountById(Integer id) {
        return this.deptMapper.queryDeptChildrenCountById(id);
    }
    @Cacheable(cacheNames = "com.sxt.system.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(cacheNames = "com.sxt.system.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
