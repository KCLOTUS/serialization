package serializer.Impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import serializer.ISerializer;
import serializer.util.FDateJsonDeserializer;
import serializer.util.FDateJsonSerializer;

import java.io.IOException;
import java.util.Date;


/**
 * Jsckson的JOSN序列化/反序列化
 */
public abstract class JSONSerializer<D> implements ISerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static  {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
        module.addSerializer(Date.class, new FDateJsonSerializer());
        module.addDeserializer(Date.class, new FDateJsonDeserializer());
        objectMapper.registerModule(module);
    }

    private static ObjectMapper getObjectMapperInstance(){
        return objectMapper;
    }

    @SuppressWarnings("unchecked")
    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            return new byte[0];
        }
        try {
            String json = objectMapper.writeValueAsString(obj);
            return json.getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        String json = new String(data);
        try {
            return (T) objectMapper.readValue(json, tClass);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public abstract void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;
}
