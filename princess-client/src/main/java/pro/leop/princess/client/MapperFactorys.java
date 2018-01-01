package pro.leop.princess.client;

import pro.leop.princess.client.invoker.IRemoteInvoker;
import pro.leop.princess.client.invoker.impl.RemoteInvokerImpl;
import pro.leop.princess.client.network.IConnectionClient;
import pro.leop.princess.client.network.impl.ConnectionClientImpl;
import pro.leop.princess.client.reflect.IMapperFactory;
import pro.leop.princess.client.reflect.impl.MapperFactoryImpl;
import pro.leop.princess.common.parser.IRequestParser;
import pro.leop.princess.common.parser.IResponseParser;
import pro.leop.princess.common.parser.impl.JsonRequestParser;
import pro.leop.princess.common.parser.impl.JsonResponseParser;

public final class MapperFactorys {
    public static IMapperFactory getMapperFactory(String host, int port) {
        IConnectionClient connectionClient = new ConnectionClientImpl(host, port);
        IRequestParser requestParser = new JsonRequestParser();
        IResponseParser responseParser = new JsonResponseParser();
        IRemoteInvoker remoteInvoker = new RemoteInvokerImpl(requestParser, responseParser, connectionClient);
        return new MapperFactoryImpl(remoteInvoker);
    }
}
