package pro.leop.princess.client.invoker.impl;

import pro.leop.princess.client.invoker.IRemoteInvoker;
import pro.leop.princess.client.network.IConnectionClient;
import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.entity.Response;
import pro.leop.princess.common.exception.RpcException;
import pro.leop.princess.common.parser.IRequestParser;
import pro.leop.princess.common.parser.IResponseParser;

import java.io.IOException;

public class RemoteInvokerImpl implements IRemoteInvoker {
    private IRequestParser requestParser;
    private IResponseParser responseParser;
    private IConnectionClient connectionClient;

    public RemoteInvokerImpl(IRequestParser requestParser, IResponseParser responseParser, IConnectionClient connectionClient) {
        this.requestParser = requestParser;
        this.responseParser = responseParser;
        this.connectionClient = connectionClient;
    }

    @Override
    public Response invoke(Request request) {
        byte[] requestBytes = requestParser.unparse(request);
        byte[] responseBytes;
        try {
            responseBytes = connectionClient.request(requestBytes);
        } catch (IOException e) {
            return new Response(request.getName(), new RpcException(e));
        }
        return responseParser.parse(responseBytes);
    }

    @Override
    public void close() {
        connectionClient.close();
    }
}
