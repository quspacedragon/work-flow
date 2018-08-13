package com.quspacedragon.workflow.enums;

import com.alibaba.common.convert.Convert;
import com.quspacedragon.workflow.entity.ProductType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * Title: UploadTypeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/8/10
 */
public enum UploadTypeEnum {
    PRODUCT_TYPE("productType", "产品类型", ProductType.class, new LinkedHashMap<String, Title>() {
        {
            put("id", new Title("id", "主键id", r -> defaultParse(r)));
            put("name", new Title("name", "名称", r -> defaultParse(r)));
            put("businessType", new Title("businessType", "业态类型", r -> Optional.ofNullable(BusinessTypeEnum.findByType(Convert.asInt(r))).map(v -> v.getName()).orElse(StringUtils.EMPTY)));
            put("code", new Title("code", "编码", r -> defaultParse(r)));
        }
    });
    private String type;
    private String name;
    private Map<String, Title> titleMap;
    private Class clazz;

    UploadTypeEnum(String type, String name, Class clazz, Map<String, Title> titleMap) {
        this.type = type;
        this.name = name;
        this.titleMap = titleMap;
        this.clazz = clazz;
    }

    @Data
    public static class Title {
        private String column;
        private String name;
        private Function<Object, String> value;

        public Title(String column, String name, Function<Object, String> value) {
            this.column = column;
            this.name = name;
            this.value = value;
        }
    }

    public static UploadTypeEnum findByType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        return Arrays.stream(values()).filter(r -> r.getType().equals(type)).findFirst().orElse(null);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Title> getTitleMap() {
        return titleMap;
    }

    public void setTitleMap(Map<String, Title> titleMap) {
        this.titleMap = titleMap;
    }

    private static String defaultParse(Object value) {
        if (value == null) {
            return StringUtils.EMPTY;
        }
        return Convert.asString(value);
    }
}
