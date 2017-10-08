package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.ScanRecord;
import com.quspacedragon.workflow.mapper.ScanRecordMapper;
import com.quspacedragon.workflow.service.IScanRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Service
public class ScanRecordService extends ServiceImpl<ScanRecordMapper, ScanRecord> implements IScanRecordService {
    public List<ScanRecord> findByBillId(Long billId) {
        ScanRecord scanRecord = new ScanRecord();
        scanRecord.setBillId(billId);
        EntityWrapper<ScanRecord> scanRecordEntityWrapper = new EntityWrapper<>();
        scanRecordEntityWrapper.setEntity(scanRecord);
        scanRecordEntityWrapper.orderBy(true, "create_time", false);
        List<ScanRecord> scanRecords = selectList(scanRecordEntityWrapper);
        return scanRecords;
    }

}
