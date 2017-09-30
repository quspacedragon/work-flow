package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Customer;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-29
 */
public interface ICustomerService extends IService<Customer> {
    Long createCustomer(Customer customer) throws Exception;
}
