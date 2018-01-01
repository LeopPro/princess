package pro.leop.princess.client.reflect;

public interface IMapperFactory {
    <T> T getMapper(Class<T> clazz);

    void close();
}
