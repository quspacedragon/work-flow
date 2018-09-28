package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Token;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface ITokenService extends IService<Token> {

    public Token findValidToken(Integer userId, Integer type);

    public Token findToken(String tokenValue, Integer type);

}
