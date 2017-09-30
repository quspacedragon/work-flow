package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.mapper.CustomerMapper;
import com.quspacedragon.workflow.service.ICustomerService;
import com.quspacedragon.workflow.util.NoUtils;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-29
 */
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Transactional(rollbackFor = Exception.class)
    public Long createCustomer(Customer customer) throws Exception {
        try {
            EnterpriseVo user = UserHelper.getUser();
            insert(customer);
            String customerNo = NoUtils.createCustomerNo(user.getId(), customer.getId());
            customer.setCustomerNo(customerNo);
            updateById(customer);
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("商户添加失败");
        }
    }
}
