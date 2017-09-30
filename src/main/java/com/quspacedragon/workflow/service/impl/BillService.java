package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.mapper.BillMapper;
import com.quspacedragon.workflow.mapper.CustomerMapper;
import com.quspacedragon.workflow.service.IBillService;
import com.quspacedragon.workflow.util.NoUtils;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Service
public class BillService extends ServiceImpl<BillMapper, Bill> implements IBillService {
    @Resource
    private CustomerMapper customerMapper;

    public Long createBill(Bill bill) throws Exception {
        try {
            EnterpriseVo user = UserHelper.getUser();
            bill.setEnterpriseId(user.getId());
            bill.setEnterpriseId(user.getId());
            insert(bill);
            Customer customer = customerMapper.selectById(bill.getCustomerId());
            String billNo = NoUtils.createBillNo(customer.getCustomerNo(), bill.getId());
            bill.setBillNo(billNo);
            updateById(bill);
            return bill.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("订单创建失败");
        }
    }

}
