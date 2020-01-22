package com.sxt.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.system.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author song
 * @data 2020/1/20
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色id删除角色与菜单的关系
     *
     * @param id
     */
    void deleteRoleMenuByRid(Serializable id);

    /**
     * 根据角色id删除角色与用户的关系
     *
     * @param id
     */
    void deleteRoleUserByRid(Serializable id);

    void deleteRoleUserByUid(Serializable id);

    void deleteRoleMenuByMid(Serializable id);

    List<Integer> queryMenuIdsByRid(Integer id);

    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    /**
     * 查询该用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Integer> queryRoleIdsByUserId(Integer userId);

    /**
     * 根据角色id查询菜单id的集合
     *
     * @param roleIds
     * @return
     */
    List<Integer> queryMenuIdsByRids(@Param("roleIds") List<Integer> roleIds);
}