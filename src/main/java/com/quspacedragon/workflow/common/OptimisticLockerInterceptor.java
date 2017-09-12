package com.quspacedragon.workflow.common;

import com.quspacedragon.workflow.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Title: OptimisticLockerInterceptor
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/12
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class OptimisticLockerInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (SqlCommandType.UPDATE != ms.getSqlCommandType()) {
            return invocation.proceed();
        }
        Object param = args[1];
        if (param instanceof HashMap) {
            Map<String, Object> map = (HashMap) param;
            if (map.containsKey("et")) {
                Object et = map.get("et");
                if (et instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) et;
                    baseEntity.setLastVer(baseEntity.getLastVer() + 1);
                    return invocation.proceed();
                }
            }

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
