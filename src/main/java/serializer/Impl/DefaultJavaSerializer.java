package serializer.Impl;

import serializer.ISerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Java默认的序列化
 */
public class DefaultJavaSerializer implements ISerializer {

    @SuppressWarnings("unchecked")
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }


    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
