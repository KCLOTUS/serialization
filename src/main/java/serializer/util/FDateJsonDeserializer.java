package serializer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FDateJsonDeserializer是对java.utl.Date类进行反序列化格式的定制化输出
 */
public class FDateJsonDeserializer extends JsonDeserializer<Date>{


    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        if (StringUtils.isNumeric(date)) {
            return new Date(Long.valueOf(date));
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
