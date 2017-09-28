package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Enterprise;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface IEnterpriseService extends IService<Enterprise> {
    Enterprise findByUsernameAndPwd(String loginName, String pwd);

}
