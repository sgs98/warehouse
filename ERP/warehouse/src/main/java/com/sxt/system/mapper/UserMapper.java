package com.sxt.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.system.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author song
 * @data 2020/1/17
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 最大排序码
     * @return
     */
    Integer queryUserMaxOrderNum();

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param rid
     */
    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}