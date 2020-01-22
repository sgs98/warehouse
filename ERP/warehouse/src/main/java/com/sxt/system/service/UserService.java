package com.sxt.system.service;

import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.system.vo.UserVo;

/**
 *
 * @author song
 * @data 2020/1/17
 */
public interface UserService extends IService<User>{
    /**
     * 根据用户登陆名查询用户信息
     */
    User queryUserByLoginName(String loginname);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 最大排序码
     * @return
     */
    Integer queryUserMaxOrderNum();

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param rids
     */
    void saveUserRole(Integer uid, Integer[] rids);
}
