package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.entity.BaseEntity;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.mapper.TokenMapper;
import com.quspacedragon.workflow.service.ITokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class TokenService extends ServiceImpl<TokenMapper, Token> implements ITokenService {
    @Override
    public Token findValidToken(Integer userId, Integer type) {
        EntityWrapper<Token> tokenEntityWrapper = new EntityWrapper<>();
        tokenEntityWrapper.eq(Token.USER_ID, userId);
        tokenEntityWrapper.eq(BaseEntity.TYPE, type);
        tokenEntityWrapper.ge(Token.EXPIRED_TIME, System.currentTimeMillis());
        Token token = selectOne(tokenEntityWrapper);
        return token;
    }


    @Override
    public Token findToken(String tokenValue,Integer type) {
        EntityWrapper<Token> tokenEntityWrapper = new EntityWrapper<>();
        tokenEntityWrapper.eq(Token.TOKEN, tokenValue);
        tokenEntityWrapper.eq(BaseEntity.TYPE, type);
        tokenEntityWrapper.ge(Token.EXPIRED_TIME, System.currentTimeMillis());
        Token token = selectOne(tokenEntityWrapper);
        return token;
    }
}
