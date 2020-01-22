package com.sxt.system.controller;

import com.sxt.system.common.ActiveUser;
import com.sxt.system.common.ResultObj;
import com.sxt.system.domain.Notice;
import com.sxt.system.service.NoticeService;
import com.sxt.system.vo.NoticeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author song
 * @data 2020/1/18
 */
@RestController
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 加载所有公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public Object loadAllNotice(NoticeVo noticeVo){
        return this.noticeService.queryAllNotice(noticeVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(Notice notice){
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            notice.setCreatetime(new Date());
            this.noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除公告
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除公告
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj bacthDeleteNotice(Integer[] ids){
        try {
            if(null!=ids&&ids.length>0){
                List<Integer> idsList=new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.noticeService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else{
                return new ResultObj(-1,"传入ID不能为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 更新公告
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(Notice notice){
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            notice.setCreatetime(new Date());
            this.noticeService.updateById(notice);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
