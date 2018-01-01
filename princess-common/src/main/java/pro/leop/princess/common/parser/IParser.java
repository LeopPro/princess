package pro.leop.princess.common.parser;

/**
 * 通用协议解析器接口
 * @param <T> 实体类型
 */
public interface IParser<T> {
    /**
     * 由二进制数据到实体，TCP粘包问题交由上层解决，本类不考虑。
     * @param data 二进制数据。
     * @return 解析后的实体
     */
    T parse(byte[] data);

    /**
     * 由实体解析数据到二进制。
     * @param object 实体
     * @return 二进制数组
     */
    byte[] unparse(T object);
}
