package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Lottery;
import com.quspacedragon.workflow.mapper.LotteryMapper;
import com.quspacedragon.workflow.service.ILotteryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-15
 */
@Service
public class LotteryService extends ServiceImpl<LotteryMapper, Lottery> implements ILotteryService {
	
}
