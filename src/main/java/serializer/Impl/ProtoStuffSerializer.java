package serializer.Impl;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import serializer.ISerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * protostuff基于Google protobuf的序列化
 */
public class ProtoStuffSerializer implements ISerializer {
    private static Map<Class<?>, Schema<?>> cachedSchema  = new ConcurrentHashMap<Class<?>, Schema<?>>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }

    @SuppressWarnings("unchecked")
    public <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            buffer.clear();
        }
    }


    public <T> T deserialize(byte[] data, Class<T> tClass) {
        try {
            T message =(T) objenesis.newInstance(tClass);
            Schema<T> schema = getSchema(tClass);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
