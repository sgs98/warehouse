package com.sxt.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.system.domain.Menu;

/**
 *
 * @author song
 * @data 2020/1/16
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询最大码
     * @return
     */
    Integer queryMenuMaxOrderNum();

    /**
     * 查询是否有子菜单
     * @param id
     * @return
     */
    Integer queryMenuChildrenCountById(Integer id);
}