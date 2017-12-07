package serializer.Impl;

import serializer.ISerializer;

public class ProtocolBufferSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return null;
    }
}
