package pro.leop.princess.server.annotation;

import java.lang.annotation.*;

/**
 * Rpc方法标识注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcMethod {
    String value();
}