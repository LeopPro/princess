package pro.leop.princess.client.network;

import java.io.IOException;

public interface IConnectionClient {

    byte[] request(byte[] requestBytes) throws IOException;

    void close();
}
