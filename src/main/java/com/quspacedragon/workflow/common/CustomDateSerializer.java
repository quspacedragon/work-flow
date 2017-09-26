package com.quspacedragon.workflow.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: CustomDateSerializer
 * <p>
 * Description: json时间格式化
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/8/26
 */
public class CustomDateSerializer extends JsonSerializer<Long> {
    private final static Logger LOGGER = Logger.getLogger(CustomDateSerializer.class);

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        try {
            if (aLong == null) {
                jsonGenerator.writeString("");
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jsonGenerator.writeString(simpleDateFormat.format(new Date(aLong)));
            }
        } catch (Exception e) {
            LOGGER.error(String.format(" time = [%s], ", aLong), e);
        }
    }
}
