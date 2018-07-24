package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Notice;
import com.quspacedragon.workflow.mapper.NoticeMapper;
import com.quspacedragon.workflow.service.INoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
	
}
