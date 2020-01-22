package com.sxt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.system.common.Constant;
import com.sxt.system.common.DataGridView;
import com.sxt.system.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.system.mapper.RoleMapper;
import com.sxt.system.domain.Role;
import com.sxt.system.service.RoleService;
/**
 *
 * @author song
 * @data 2020/1/20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        IPage page=new Page(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        qw.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        qw.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        this.roleMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public Role saveRole(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        this.roleMapper.updateById(role);
        return role;
    }

    @Override
    public List<Integer> queryMenuIdsByRid(Integer id) {
        return this.roleMapper.queryMenuIdsByRid(id);
    }

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param mids
     */
    @Override
    public void saveRoleMenu(Integer rid, Integer[] mids) {
        //根据角色rid删除角色与菜单的关系
        this.roleMapper.deleteRoleMenuByRid(rid);
        if(null!=mids&&mids.length>0){
            for (Integer mid : mids) {
                this.roleMapper.insertRoleMenu(rid,mid);
            }
        }
    }

    /**
     * 按用户id查询该用户拥有的角色
     * @param roleVo
     * @return
     */
    @Override
    public DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo) {
        QueryWrapper<Role> qw=new QueryWrapper<>();
        qw.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        List<Role> roles = this.roleMapper.selectList(qw);
        //根据用户ID查询已拥有的角色ID
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(roleVo.getUserId());

        List<Map<String,Object>> lists=new ArrayList<>();

        for (Role role : roles) {
            Boolean LAY_CHECKED=false;
            for (Integer roleId : roleIds) {
                if(role.getId().equals(roleId)){
                    LAY_CHECKED=true;
                    break;
                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("id",role.getId());
            map.put("name",role.getName());
            map.put("remark",role.getRemark());
            map.put("LAY_CHECKED",LAY_CHECKED);
            lists.add(map);
        }
        return new DataGridView(Long.valueOf(lists.size()),lists);
    }

    /**
     * shiro中查询角色
     * @param id
     * @return
     */
    @Override
    public List<String> queryRoleNamesByUserId(Integer id) {
        //根据用户id查询角色id的集合
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(id);

        if(null!=roleIds&&roleIds.size()>0){
            QueryWrapper<Role> qw=new QueryWrapper<>();
            qw.eq("available", Constant.AVAILABLE_TRUE);
            qw.in("id",roleIds);
            List<Role> rolesObject=this.roleMapper.selectList(qw);
            List<String> roles=new ArrayList<>();
            for (Role role : rolesObject) {
                roles.add(role.getName());
            }
            return roles;
        }else{
            return null;
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据角色id删除角色与菜单的关系
        this.roleMapper.deleteRoleMenuByRid(id);
        //根据角色id删除角色与用户之间的关系
        this.roleMapper.deleteRoleUserByRid(id);
        return super.removeById(id);
    }
}
