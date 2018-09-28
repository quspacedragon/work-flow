package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.CheckRecord;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
public interface ICheckRecordService extends IService<CheckRecord> {

    CheckRecord findNowCheckRecord(Integer equipEntityId);

}
