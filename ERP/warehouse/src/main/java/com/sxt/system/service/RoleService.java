package com.sxt.system.service;

import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.system.vo.RoleVo;

import java.util.List;

/**
 *
 * @author song
 * @data 2020/1/20
 */
public interface RoleService extends IService<Role>{

    /**
     * 查询角色
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 保存角色
     * @param role
     * @return
     */
    Role saveRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    Role updateRole(Role role);

    /**
     * 根据角色id查询拥有的菜单
     * @return
     */
    List<Integer> queryMenuIdsByRid(Integer id);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param mids
     */
    void saveRoleMenu(Integer rid, Integer[] mids);

    /**
     * 按用户id查询该用户拥有的角色
     * @param roleVo
     * @return
     */

    DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo);

    /**
     * 根据用户id查询角色名称的集合
     * @param id
     * @return
     */
    List<String> queryRoleNamesByUserId(Integer id);

}
