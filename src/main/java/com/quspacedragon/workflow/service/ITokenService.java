package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.Token;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public interface ITokenService extends IService<Token> {
    /**
     * 查询可用的token
     *
     * @param userId
     * @return
     */
    public Token findValidToken(Long userId, String tokenValue, int type);

    public Token findByEnterpriseId(Long userId, int type);

}
