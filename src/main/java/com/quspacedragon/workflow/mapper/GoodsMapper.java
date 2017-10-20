package com.quspacedragon.workflow.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.quspacedragon.workflow.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> findByCustomerId(@Param(value = "customerId") Long customerId, @Param(value = "startTime") Long startTime, @Param(value = "endTime") Long endTime);

}