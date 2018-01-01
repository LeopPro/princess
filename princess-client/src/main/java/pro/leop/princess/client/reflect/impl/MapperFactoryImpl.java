package pro.leop.princess.client.reflect.impl;

import pro.leop.princess.client.annotation.RpcCall;
import pro.leop.princess.client.annotation.RpcMapper;
import pro.leop.princess.client.invoker.IRemoteInvoker;
import pro.leop.princess.client.reflect.IMapperFactory;
import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.entity.Response;
import pro.leop.princess.common.exception.RpcCallException;
import pro.leop.princess.common.exception.RpcMapperException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperFactoryImpl implements IMapperFactory {
    private IRemoteInvoker remoteInvoker;

    public MapperFactoryImpl(IRemoteInvoker remoteInvoker) {
        this.remoteInvoker = remoteInvoker;

    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        RpcMapper rpcMapper = clazz.getAnnotation(RpcMapper.class);
        if (rpcMapper == null) {
            throw new RpcMapperException(clazz.toString() + "没有被标记 RpcMapper 注解");
        }
        String facadePath = rpcMapper.value();
        T instance = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperInvocationHandler(facadePath));
        return instance;
    }

    @Override
    public void close() {
        remoteInvoker.close();
    }

    private class MapperInvocationHandler implements InvocationHandler {
        private String facadePath;

        public MapperInvocationHandler(String facadePath) {
            this.facadePath = facadePath;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            RpcCall rpcCall = method.getAnnotation(RpcCall.class);
            if (rpcCall == null) {
                throw new RpcCallException(method.toString() + "没有被 RpcCall 注解标记");
            }
            Request request = new Request();
            request.setName(facadePath + rpcCall.value());
            request.setParameters(args);
            Response response = remoteInvoker.invoke(request);
            System.out.println(response);
            if (!response.isSuccess()) {
                throw new RpcCallException(response.getExceptionMessage());
            }
            return response.getResult();
        }
    }

}
