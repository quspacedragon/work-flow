package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.Customer;

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

    Customer findByUsernameAndPwd(String loginName, String pwd);
}
