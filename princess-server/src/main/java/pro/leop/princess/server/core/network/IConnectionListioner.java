package pro.leop.princess.server.core.network;

import pro.leop.princess.server.core.processor.IProcessor;

/**
 * 连接监听器接口
 */
public interface IConnectionListioner {
    /**
     * 注入Processor
     * @param processor 执行器
     */
    void setProcessor(IProcessor processor);

    /**
     * 设置监听端口
     * @param port 端口号
     */
    void setPort(int port);

    /**
     * 开始监听
     */
    void start();

}