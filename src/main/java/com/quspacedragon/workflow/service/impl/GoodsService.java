package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Goods;
import com.quspacedragon.workflow.mapper.GoodsMapper;
import com.quspacedragon.workflow.service.IGoodsService;
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
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	
}
