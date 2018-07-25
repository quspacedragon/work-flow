package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.SmsRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface ISmsRecordService extends IService<SmsRecord> {

    public Integer countTodayByPhoneAndType(String phone, Integer type);

    public SmsRecord findLastSmsRecord(String phone, Integer type);

}
