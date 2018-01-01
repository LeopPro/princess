package pro.leop.princess.client.annotation;

import java.lang.annotation.*;

/**
 * Rpc方法标识注解
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcMapper {
    String value();
}