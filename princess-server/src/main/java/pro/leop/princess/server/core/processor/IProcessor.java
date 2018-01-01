package pro.leop.princess.server.core.processor;

/**
 * 过程执行器，执行主要的逻辑
 */
public interface IProcessor {
    /**
     * 执行
     * @param requestData 请求数据
     * @return 响应数据
     */
    byte[] process(byte[] requestData);
}
