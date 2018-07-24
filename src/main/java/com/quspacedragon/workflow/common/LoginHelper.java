/**
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.quspacedragon.workflow.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.exception.ExceptionEnum;
import com.quspacedragon.workflow.service.ITokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import sun.management.snmp.util.MibLogger;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
@Log4j
public class LoginHelper implements InitializingBean {
    private static Cache<String, Token> cache;
    @Resource
    private ITokenService tokenService;


    public boolean islogin(Integer userId, String token, Integer type) {
        Token tokenEntity = selectToken(userId, type);
        if (tokenEntity == null) {
            return false;
        }
        Date expiredTime = tokenEntity.getExpiredTime();
        if (expiredTime.after(new Date())) {
            throw new BizException(ExceptionEnum.TOKEN_EXPIRE);
        }
        return true;
    }

    private Token selectToken(Integer userId, Integer type) {
        try {
            cache.get(userId + "_" + type, new Callable<Token>() {
                @Override
                public Token call() throws Exception {
                    return tokenService.findValidToken(userId, type);
                }
            });
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            throw new BizException(e.getMessage(), ExceptionEnum.UNKNOW_ERROR.getCode());
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();
    }
}
