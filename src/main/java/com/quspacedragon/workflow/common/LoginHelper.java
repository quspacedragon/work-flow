/**
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.quspacedragon.workflow.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.exception.ExceptionEnum;
import com.quspacedragon.workflow.service.ITokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
@Log4j
public class LoginHelper implements InitializingBean {
    public static final String LOGIN_TYPE = "login_type";
    private static Cache<String, Token> cache;
    public static BiMap<Integer, String> userMap = HashBiMap.create();
    public static BiMap<Integer, String> adminMap = HashBiMap.create();
    public static BiMap<Integer, String> staffMap = HashBiMap.create();
    @Resource
    private ITokenService tokenService;


    public Integer getLoginUserId(String token, HttpServletRequest httpServletRequest) {
        Integer type = (Integer) httpServletRequest.getAttribute(LOGIN_TYPE);
        return getLoginMap(type).inverse().get(token);

    }

    public String getUUID(Integer userId, Integer type) {
        String userToken = UUID.randomUUID().toString();
        getLoginMap(type).put(userId, userToken);
        return userToken;
    }


    public boolean islogin(Integer userId, String token, Integer type) {
        boolean islogin = false;
        Map<Integer, String> map = getLoginMap(type);
        if (map.containsKey(userId) && token.equals(map.get(userId))) {
            return true;
        } else {
            Token tokenEntity = selectToken(userId, type);
            if (tokenEntity == null) {
                return false;
            }
            if (!tokenEntity.getToken().equalsIgnoreCase(token)) {
                return false;
            }
            Date expiredTime = tokenEntity.getExpiredTime();
            if (expiredTime.after(new Date())) {
                throw new BizException(ExceptionEnum.TOKEN_EXPIRE);
            }
            return true;
        }
    }

    private BiMap<Integer, String> getLoginMap(Integer type) {
        BiMap<Integer, String> map = null;
        LoginUserTypeEnum loginUserTypeEnum = LoginUserTypeEnum.findByType(type);
        if (LoginUserTypeEnum.ADMIN.getType() == loginUserTypeEnum.getType()) {
            map = adminMap;
        } else if (LoginUserTypeEnum.USER.getType() == loginUserTypeEnum.getType()) {
            map = userMap;
        } else if (LoginUserTypeEnum.STAFF.getType() == loginUserTypeEnum.getType()) {
            map = staffMap;
        }
        return map;
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
