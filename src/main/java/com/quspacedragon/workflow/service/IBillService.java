package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Bill;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface IBillService extends IService<Bill> {
    /**
     * 创建订单
     *
     * @param bill
     * @return
     * @throws Exception
     */
    Long createBill(Bill bill) throws Exception;

    /**
     * 查询未完成的订单
     *
     * @param goodsId
     * @return
     */
    int countUnComplete(long goodsId);
}
