package serializer.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import serializer.ISerializer;

/**
 * fastjson实现JSON序列化/反序列化
 *
 * @author bfy
 * @version 1.0.0
 */
public class JSON2Serializer implements ISerializer{

    @Override
    public <T> byte[] serialize(T obj) {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return (T) JSON.parseObject(new String(data), tClass);
    }
}
