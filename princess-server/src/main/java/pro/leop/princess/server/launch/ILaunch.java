package pro.leop.princess.server.launch;

/**
 * 启动器接口
 */
public interface ILaunch {
    /**
     * 设置服务器监听端口
     * @param port 端口号
     */
    void setPort(int port);

    void setPackagePath(String packagePath);

    /**
     * 运行服务器
     */
    void run();
}
