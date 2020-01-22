package com.sxt.system.controller;

import com.sxt.system.common.Constant;
import com.sxt.system.common.DataGridView;
import com.sxt.system.common.ResultObj;
import com.sxt.system.domain.Menu;
import com.sxt.system.service.MenuService;
import com.sxt.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author song
 * @data 2020/1/20
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /***
     * 查询菜单和权限
     * @param menuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo){
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 查询菜单
     * @return
     */
    @GetMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo){
        List<Menu> menus = this.menuService.queryAllMenuForList();
        return new DataGridView(Long.valueOf(menus.size()),menus);
    }

    /**
     * 查询菜单和权限最大的排序码
     */

    @GetMapping("queryMenuMaxOrderNum")
    public Object queryMenuMaxOrderNum(){
        Integer maxValue=this.menuService.queryMenuMaxOrderNum();
        return new DataGridView(maxValue+1);
    }
    /**
     * 添加菜单和权限
     */
    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu){
        try {
            if(menu.getType().equals(Constant.MENU_TYPE_TOP)){
                menu.setPid(0);
            }
            menu.setSpread(Constant.SPREAD_FALSE);
            menu.setAvailable(Constant.AVAILABLE_TRUE);
            Menu d=this.menuService.saveMenu(menu);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改菜单和权限
     */
    @PostMapping("updateMenu")
    public ResultObj updateMenu(Menu menu){
        try {
            Menu d=this.menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     *装载数据表格查询菜单和权限
     */
    @GetMapping("getMenuById")
    public Object getMenuById(Integer id){
        return new DataGridView(this.menuService.getById(id));
    }

    /*
     * 检查当前ID的菜单和权限是否有子菜单和权限
     * */
    @GetMapping("getMenuHasChildrenCountById")
    public Object checkMenuHasChildrenNode(Integer id){
        Integer count=this.menuService.queryMenuChildrenCountById(id);
        return new DataGridView(count);
    }
    /**
     * 删除菜单和权限
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id){
        try {
            this.menuService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

