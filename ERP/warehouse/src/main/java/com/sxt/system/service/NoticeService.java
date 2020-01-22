package com.sxt.system.service;

import com.sxt.system.common.DataGridView;
import com.sxt.system.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.system.vo.NoticeVo;

/**
 *
 * @author song
 * @data 2020/1/18
 */
public interface NoticeService extends IService<Notice>{


        DataGridView queryAllNotice(NoticeVo noticeVo);
    }
