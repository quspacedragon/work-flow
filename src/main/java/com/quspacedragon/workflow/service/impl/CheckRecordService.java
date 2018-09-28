package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.CheckRecord;
import com.quspacedragon.workflow.mapper.CheckRecordMapper;
import com.quspacedragon.workflow.service.ICheckRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@Service
public class CheckRecordService extends ServiceImpl<CheckRecordMapper, CheckRecord> implements ICheckRecordService {

    @Override
    public CheckRecord findNowCheckRecord(Integer checkProject) {


//        baseMapper.selectById()
        return null;
    }
}
