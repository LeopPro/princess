package pro.leop.princess.client.network.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.leop.princess.client.network.IConnectionClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionClientImpl implements IConnectionClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionClientImpl.class);
    private String host;
    private int port;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ConnectionClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("Socket 错误", e);
        }
    }

    @Override
    public byte[] request(byte[] requestBytes) throws IOException {
        outputStream.writeInt(requestBytes.length);
        outputStream.write(requestBytes);
        int responseLength = inputStream.readInt();
        byte[] responseBytes = new byte[responseLength];
        inputStream.read(responseBytes);
        return responseBytes;
    }

    @Override
    public void close() {
        try {
            outputStream.writeInt(-1);
            outputStream.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
