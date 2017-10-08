package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.ScanRecord;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface IScanRecordService extends IService<ScanRecord> {
        List<ScanRecord> findByBillId(Long billId);

}
