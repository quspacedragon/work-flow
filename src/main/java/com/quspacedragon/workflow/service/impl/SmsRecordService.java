package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.BaseEntity;
import com.quspacedragon.workflow.entity.SmsRecord;
import com.quspacedragon.workflow.enums.SmsRecordStatus;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.mapper.SmsRecordMapper;
import com.quspacedragon.workflow.service.ISmsRecordService;
import com.quspacedragon.workflow.util.DateUtil;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class SmsRecordService extends ServiceImpl<SmsRecordMapper, SmsRecord> implements ISmsRecordService {

    @Override
    public Integer countTodayByPhoneAndType(String phone, Integer type) {
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setPhone(phone);
        smsRecord.setType(type);
        EntityWrapper<SmsRecord> smsRecordEntityWrapper = new EntityWrapper<>(smsRecord);
        smsRecordEntityWrapper.le(BaseEntity.CREATE_TIME, DateUtil.getTodayEnd());
        smsRecordEntityWrapper.ge(BaseEntity.CREATE_TIME, DateUtil.getTodayStart());
        return this.selectCount(smsRecordEntityWrapper);
    }


    @Override
    public SmsRecord findLastSmsRecord(String phone, Integer type) {
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setPhone(phone);
        smsRecord.setType(type);
        smsRecord.setStatus(SmsRecordStatus.not_use.getSattus());
        EntityWrapper<SmsRecord> smsRecordEntityWrapper = new EntityWrapper<>(smsRecord);
        smsRecordEntityWrapper.orderBy(BaseEntity.CREATE_TIME, false);
        Page<SmsRecord> page = new Page();
        page.setCurrent(1);
        page.setSize(1);
        page = this.selectPage(page, smsRecordEntityWrapper);
        return java.util.Optional.ofNullable(page).map(r -> r.getRecords().get(0)).orElse(null);
    }

    @Override
    public Boolean valiade(String phone, Integer type, String code) {
        SmsRecord lastSmsRecord = findLastSmsRecord(phone, type);
        if (lastSmsRecord == null) {
            throw new BizException("无效的验证码");
        }
        lastSmsRecord.setStatus(SmsRecordStatus.userd.getSattus());
        boolean b = this.updateById(lastSmsRecord);
        String codeData = lastSmsRecord.getCode();
        if (!codeData.equals(code)) {
            throw new BizException("验证码错误");
        }
        return true;
    }
}
