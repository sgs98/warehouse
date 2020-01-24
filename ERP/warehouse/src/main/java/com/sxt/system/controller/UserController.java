package com.sxt.system.controller;

import com.sxt.system.common.*;
import com.sxt.system.domain.User;
import com.sxt.system.service.UserService;
import com.sxt.system.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author song
 * @data 2020/1/20
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    /***
     * 查询用户
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public Object loadAllUser(UserVo userVo){
        return this.userService.queryAllUser(userVo);
    }

    /**
     * 添加用户
     */
    @PostMapping("addUser")
    public ResultObj addUser(User user){
        try {
            user.setSalt(MD5Utils.createUUID());
            user.setType(Constant.USER_TYPE_NORMAL);
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD,user.getSalt(),2));
            user.setAvailable(Constant.AVAILABLE_TRUE);
            user.setImgpath(Constant.DEFAUTL_IMAGE);
            this.userService.saveUser(user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改用户
     */
    @PostMapping("updateUser")
    public ResultObj updateUser(User user){
        try {
            this.userService.updateUser(user);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 重置 密码
     */
    @PostMapping("resetUserPwd")
    public ResultObj resetUserPwd(Integer id){
        try {
            User user=new User();
            user.setId(id);
            user.setSalt(MD5Utils.createUUID());
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD,user.getSalt(),2));
            this.userService.updateUser(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }
    /**
     * 删除用户
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try {
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 最大排序码
     */
    @GetMapping("queryUserMaxOrderNum")
    public Object queryUserMaxOrderNum(){
        Integer maxValue=this.userService.queryUserMaxOrderNum();
        return new DataGridView(maxValue+1);
    }

    /**
     * 保存用户和角色之间的关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid,Integer[] rids){
        try {

            this.userService.saveUserRole(uid,rids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
    /**
     * 查询当前登陆用户信息
     */
    @GetMapping("getCurrentUser")
    public Object geCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        return new DataGridView(activeUser.getUser());
    }
}
