package pro.leop.princess.server.entity;


import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.entity.Response;
import pro.leop.princess.common.exception.RpcException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * RPC函数执行过程
 */
public class Executer implements Cloneable {
    private String name;
    private Object instance;
    private Method method;
    private Request request;

    public Executer(String name, Object instance, Method method) {
        this.name = name;
        this.instance = instance;
        this.method = method;
    }

    /**
     * 执行相应的RPC函数
     *
     * @return 返回一个response实体
     */
    public Response execute() {
        if (method == null || instance == null) {
            return new Response(name, new RpcException("Rpc方法 " + name + " 未找到"));
        }
        Object[] parameters = request.getParameters();
        Object result;
        try {
            result = method.invoke(instance, parameters);
        } catch (Exception e) {
            return new Response(name, new RpcException(e.getClass().toString() +" "+ e.getMessage()));
        }
        return new Response(name, result);
    }

    public String getName() {
        return name;
    }

    @Override
    public Executer clone() {
        Executer executer = new Executer(name, instance, method);
        executer.setRequest(request);
        return executer;
    }

    @Override
    public String toString() {
        return "Executer{" +
                "name='" + name + '\'' +
                ", instance=" + instance +
                ", method=" + method +
                '}';
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
