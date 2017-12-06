package serializer.Impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import serializer.ISerializer;

/**
 * XStream实现XML序列化/反序列化
 */
public class XmlSerializer implements ISerializer{
    //初始化XStream对象
    private static final XStream xStream = new XStream(new DomDriver());

    @SuppressWarnings("unchecked")
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        String xml = new String(data);
        return (T) xStream.fromXML(xml);
    }
}
