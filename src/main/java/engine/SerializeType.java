package engine;

import org.apache.commons.lang.StringUtils;

/**
 * 序列化类型枚举
 *
 * @author flytoyou
 * @version 1.0.0
 */
public enum SerializeType {

    DefaultJavaSerializer("DefaultJavaSerializer"),
    HessianSerializer("HessianSerializer"),
    JSON2Serializer("JSON2Serializer"),
    ProtoStuffSerializer("ProtoStuffSerializer"),
    XmlSerializer("XmlSerializer"),
    MarshallingSerializer("MarshallingSerializer"),
    AvroSerializer("AvroSerializer"),
    ProtocolBufferSerializer("ProtocolBufferSerializer"),
    ThriftSerializer("ThriftSerializer");

    private String serializeType;

    private SerializeType(String serializerType){
        this.serializeType = serializerType;
    }

    public static SerializeType queryByType(String serializerType){
        if (StringUtils.isBlank(serializerType)){
            return null;
        }
        for (SerializeType serialize : SerializeType.values()){
            if (StringUtils.equals(serializerType,serialize.getSerializeType())){
                return serialize;
            }
        }
        return null;
    }

    public String getSerializeType() {
        return serializeType;
    }
}
