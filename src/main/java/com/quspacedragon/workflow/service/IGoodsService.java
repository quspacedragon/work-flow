package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.Goods;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface IGoodsService extends IService<Goods> {

    List<Goods> findByCustomerId(Long customerId, Long startTime, Long endTime);

}
