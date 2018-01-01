package pro.leop.princess.server.core.network.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.leop.princess.server.core.network.IConnectionListioner;
import pro.leop.princess.server.core.processor.IProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO连接监听器
 */
public class BioConnectionListioner implements IConnectionListioner {
    private final static Logger LOGGER = LoggerFactory.getLogger(BioConnectionListioner.class);
    private int port;
    private IProcessor processor;

    @Override
    public void setProcessor(IProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        LOGGER.info("开始监听 {} 端口", port);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new BioRunnable(socket)).start();
            }
        } catch (IOException e) {
            LOGGER.error("端口绑定错误", e);
        }
    }

    private class BioRunnable implements Runnable {
        private Socket socket;

        private BioRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream inputStream;
            DataOutputStream outputStream;
            try {
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    int size = inputStream.readInt();
                    if (size==-1){
                        LOGGER.info("客户端请求结束链接");
                        socket.close();
                        break;
                    }
                    LOGGER.info("接收到请求，长度：{}", size);
                    byte[] request = new byte[size];
                    inputStream.read(request);
                    LOGGER.info("接收到请求，内容：{}", new String(request));
                    byte[] response = processor.process(request);
                    LOGGER.info("发送相应，内容：{}", new String(response));
                    outputStream.writeInt(response.length);
                    outputStream.write(response);
                }
            } catch (IOException e) {
                LOGGER.error("流错误", e);
            }
        }
    }
}
