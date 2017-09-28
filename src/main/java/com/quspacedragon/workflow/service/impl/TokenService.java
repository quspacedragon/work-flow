package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.mapper.TokenMapper;
import com.quspacedragon.workflow.service.ITokenService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Service
public class TokenService extends ServiceImpl<TokenMapper, Token> implements ITokenService {

    /**
     * 查询可用的token
     *
     * @param userId
     * @return
     */
    public Token findValidToken(Long userId, String tokenValue) {
        Token token = new Token();
        token.setEnterpriseId(userId);
        token.setToken(tokenValue);
        EntityWrapper<Token> tokenEntityWrapper = new EntityWrapper<Token>();
        tokenEntityWrapper.setEntity(token);
        tokenEntityWrapper.gt(true, Token.EXPIRED_TIME, System.currentTimeMillis());
        return selectOne(tokenEntityWrapper);
    }

    public Token findByEnterpriseId(Long userId) {
        Token token = new Token();
        token.setEnterpriseId(userId);
        EntityWrapper<Token> tokenEntityWrapper = new EntityWrapper<Token>();
        tokenEntityWrapper.setEntity(token);
        return selectOne(tokenEntityWrapper);
    }

}
