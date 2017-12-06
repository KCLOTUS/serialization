package serializer;

/**
 * 定义序列化/反序列化通用接口
 *
 * @author flytoyou
 * @version 1.0.0
 */
public interface ISerializer {

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    public <T> byte[] serialize(T obj);

    /**
     * 反序列化
     * @param data
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T deserialize(byte[] data, Class<T> tClass);
}
