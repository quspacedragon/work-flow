package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.mapper.BillMapper;
import com.quspacedragon.workflow.service.IBillService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-24
 */
@Service
public class BillService extends ServiceImpl<BillMapper, Bill> implements IBillService {
	
}
