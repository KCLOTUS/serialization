package serializer.Impl;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import serializer.ISerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Avro序列化/反序列化
 *
 * @author flytoyou
 * @version 1.0.0
 */
public class AvroSerializer implements ISerializer{

    @Override
    public <T> byte[] serialize(T obj) {
        try{
            if(!(obj instanceof SpecificRecordBase)){
                throw new UnsupportedOperationException("not supported type");
            }
            DatumWriter userDatumWriter = new SpecificDatumWriter(obj.getClass());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream,null);
            userDatumWriter.write(obj,binaryEncoder);
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try{
            if (!SpecificRecordBase.class.isAssignableFrom(clazz)){
                throw new UnsupportedOperationException("not supported clazz type");
            }
            DatumReader userDatumReader = new SpecificDatumReader(clazz);
            BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data),null);
            return (T) userDatumReader.read(clazz.newInstance(),binaryDecoder);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
