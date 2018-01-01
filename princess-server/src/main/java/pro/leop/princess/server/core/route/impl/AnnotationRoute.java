package pro.leop.princess.server.core.route.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.leop.princess.common.entity.Request;
import pro.leop.princess.server.annotation.RpcFacade;
import pro.leop.princess.server.annotation.RpcMethod;
import pro.leop.princess.server.core.ClasspathPackageScanner;
import pro.leop.princess.server.entity.Executer;
import pro.leop.princess.server.core.route.IRoute;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用注解标记的请求路由器,面条代码待重构
 */
public class AnnotationRoute implements IRoute {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnnotationRoute.class);
    private final Map<String, Executer> executerMap = new ConcurrentHashMap<>();

    public AnnotationRoute(String packagePath) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        ClasspathPackageScanner packageScanner = new ClasspathPackageScanner(packagePath, classLoader);
        List<String> classNameList;
        try {
            classNameList = packageScanner.getFullyQualifiedClassNameList();
        } catch (IOException e) {
            LOGGER.error("包扫描异常", e);
            throw new RuntimeException(e);
        }
        try {
            for (String className : classNameList) {
                Class<?> clazz = classLoader.loadClass(className);
                RpcFacade rpcFacade = clazz.getAnnotation(RpcFacade.class);
                if (rpcFacade != null) {
                    final String facadeName = rpcFacade.value();
                    LOGGER.info("扫描到RpcFacade {},RpcFacadeName: {}", clazz.getName(), facadeName);
                    final Object instance = clazz.newInstance();
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        RpcMethod rpcMethod = method.getAnnotation(RpcMethod.class);
                        if (rpcMethod != null) {
                            final String methodName = rpcMethod.value();
                            final String fullName = facadeName + methodName;
                            LOGGER.info("扫描到RpcMethod,将RpcMethod[{}]绑定到 {}", fullName, method);
                            Executer executer = new Executer(fullName, instance, method);
                            executerMap.put(fullName, executer);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            LOGGER.error("执行异常", e);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            LOGGER.error("无法实例化RpcFacade，请检查RpcFacade是否有公开的空构造器，是否抛出异常", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Executer route(Request request) {
        Executer executer = executerMap.get(request.getName());
        if (executer == null) {
            //异常也应该封装成Response，对于不存在的rpc方法，也返回一个Executer，方便在Executer中统一进行异常处理。
            return new Executer(request.getName(), null, null);
        }
        executer = executer.clone();
        executer.setRequest(request);
        return executer;
    }

}
