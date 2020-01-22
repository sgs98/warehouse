package com.sxt.system.service;

import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.system.vo.MenuVo;

import java.util.List;

/**
 *
 * @author song
 * @data 2020/1/16
 */
public interface MenuService extends IService<Menu>{

    /**
     * 全查询菜单
     * @return
     */
    List<Menu> queryAllMenuForList();

    /**
     * 根据用户id查询菜单
     * @param id
     * @return
     */
    List<Menu> queryMenuForListByUsersId(Integer id);

    /**
     * 查询全部菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 查询最大排序码
     * @return
     */
    Integer queryMenuMaxOrderNum();

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    Menu saveMenu(Menu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    Menu updateMenu(Menu menu);

    /**
     * 检查菜单中是否有子菜单
     * @param id
     * @return
     */
    Integer queryMenuChildrenCountById(Integer id);

    /**
     * 根据用户id查询权限编码
     * @param id
     * @return
     */
    List<String> queryPermissionCodesByUserId(Integer id);
}
