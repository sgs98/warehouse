package com.sxt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxt.system.common.Constant;
import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.Menu;
import com.sxt.system.mapper.RoleMapper;
import com.sxt.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.system.mapper.MenuMapper;
import com.sxt.system.service.MenuService;
/**
 *
 * @author song
 * @data 2020/1/16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Menu> queryAllMenuForList() {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Constant.MENU_TYPE_TOP)
                        .or().eq("type",Constant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }

    /**
     *根据用户查询拥有的菜单
     * @param id 用户id
     * @return
     */
    @Override
    public List<Menu> queryMenuForListByUsersId(Integer id) {
        //根据userid查询角色id的集合
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(id);

        //根据角色ID的集合，查询菜单的ID的集合
        if(null!=roleIds&&roleIds.size()>0){
            List<Integer> menuIds=this.roleMapper.queryMenuIdsByRids(roleIds);
            if(null!=menuIds&&menuIds.size()>0) {
                QueryWrapper<Menu> qw = new QueryWrapper<>();
                qw.eq("available", Constant.AVAILABLE_TRUE);
                qw.and(new Consumer<QueryWrapper<Menu>>() {
                    @Override
                    public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                        menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP)
                                .or().eq("type", Constant.MENU_TYPE_LEFT);
                    }
                });
                qw.in("id",menuIds);
                qw.orderByAsc("ordernum");
                List<Menu> menus=this.menuMapper.selectList(qw);
                return menus;
            }else{
                return new ArrayList<>();
            }
        }else{
            return new ArrayList<>();
        }


    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.orderByAsc("ordernum");
        qw.eq(menuVo.getAvailable()!=null,"available",menuVo.getAvailable());
        List<Menu> menus = this.menuMapper.selectList(qw);
        return new DataGridView(Long.valueOf(menus.size()),menus);
    }

    @Override
    public Integer queryMenuMaxOrderNum() {
        return this.menuMapper.queryMenuMaxOrderNum();
    }

    @Override
    public Menu saveMenu(Menu menu) {
        this.menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        this.menuMapper.updateById(menu);
        return menu;
    }

    @Override
    public boolean removeById(Serializable id) {
        roleMapper.deleteRoleMenuByMid(id);
        return super.removeById(id);
    }

    @Override
    public Integer queryMenuChildrenCountById(Integer id) {
        return this.menuMapper.queryMenuChildrenCountById(id);
    }

    /**
     * 根据用户id查询权限编码
     * shiro中查询权限编码
     * @param id
     * @return
     */
    @Override
    public List<String> queryPermissionCodesByUserId(Integer id) {
        //根据userid查询角色id的集合
        List<Integer> roleIds=this.roleMapper.queryRoleIdsByUserId(id);

        //根据角色ID的集合，查询菜单的ID的集合
        if(null!=roleIds&&roleIds.size()>0){
            List<Integer> menuIds=this.roleMapper.queryMenuIdsByRids(roleIds);
            if(null!=menuIds&&menuIds.size()>0) {
                QueryWrapper<Menu> qw = new QueryWrapper<>();
                qw.eq("available", Constant.AVAILABLE_TRUE);
                qw.eq("type",Constant.MENU_TYPE_PERMISSION);
                qw.in("id",menuIds);
                qw.orderByAsc("ordernum");
                List<Menu> menus=this.menuMapper.selectList(qw);
                List<String> permissions=new ArrayList<>();
                for (Menu menu : menus) {
                    permissions.add(menu.getTypecode());
                }
                return permissions;
            }else{
                return null;
            }
        }else{
            return null;
        }


    }


}
