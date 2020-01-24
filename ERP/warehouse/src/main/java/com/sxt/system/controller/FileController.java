package com.sxt.system.controller;

import com.sxt.system.common.ActiveUser;
import com.sxt.system.common.DataGridView;
import com.sxt.system.controller.upload.UploadProperties;
import com.sxt.system.controller.upload.UploadService;
import com.sxt.system.domain.User;
import com.sxt.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author song
 * @data 2020/1/22
 */
@RestController
@RequestMapping("api/file")
public class FileController {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadProperties uploadProperties;

    /**
     * 上传文件
     */
    @RequestMapping("uploadFile")
    public Object uploadFile(MultipartFile mf){

        String path = this.uploadService.uploadImage(mf);
        String baseUrl = uploadProperties.getBaseUrl();

        Map<String,String> map=new HashMap<>();
        map.put("src",baseUrl+path);
        //更新数据库
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        User user = activeUser.getUser();
        user.setImgpath(path);
        userService.updateUser(user);
        return new DataGridView(map);
    }
    @RequestMapping("uploadGoodsFile")
    public Object uploadGoodsFile(MultipartFile mf){
        String path = this.uploadService.uploadImage(mf);

        Map<String,String> map=new HashMap<>();
        map.put("src",path);
        return new DataGridView(map);
    }

}
