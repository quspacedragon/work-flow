package com.quspacedragon.workflow.common;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * Title: MyMetaObjectHandler
 * <p>
 * Description: 自动填充
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/12
 */
public class MyMetaObjectHandler extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object isValid = getFieldValByName("isValid", metaObject);//mybatis-plus版本2.0.9+
        if (isValid == null) {
            setFieldValByName("isValid", 1, metaObject);//mybatis-plus版本2.0.9+
        }


        Object opTime = getFieldValByName("opTime", metaObject);//mybatis-plus版本2.0.9+
        if (opTime == null) {
            setFieldValByName("opTime", System.currentTimeMillis(), metaObject);//mybatis-plus版本2.0.9+
        }


        Object lastVer = getFieldValByName("lastVer", metaObject);//mybatis-plus版本2.0.9+
        if (lastVer == null) {
            setFieldValByName("lastVer", 0, metaObject);//mybatis-plus版本2.0.9+
        }
        Object status = getFieldValByName("status", metaObject);//mybatis-plus版本2.0.9+
        if (status == null) {
            setFieldValByName("status", 0, metaObject);//mybatis-plus版本2.0.9+
        }
        Object type = getFieldValByName("type", metaObject);//mybatis-plus版本2.0.9+
        if (type == null) {
            setFieldValByName("type", 0, metaObject);//mybatis-plus版本2.0.9+
        }
        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+
        if (createTime == null) {
            setFieldValByName("createTime", System.currentTimeMillis(), metaObject);//mybatis-plus版本2.0.9+
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object opTime = getFieldValByName("opTime", metaObject);//mybatis-plus版本2.0.9+
        if (opTime == null) {
            setFieldValByName("opTime", System.currentTimeMillis(), metaObject);//mybatis-plus版本2.0.9+
        }
    }
}
