package pro.leop.princess.client;

import pro.leop.princess.client.annotation.RpcCall;
import pro.leop.princess.client.annotation.RpcMapper;

import java.util.concurrent.Future;

@RpcMapper("facade.")
public interface TestMapper {
    @RpcCall("method1")
    String test1(int val);

    @RpcCall("method2")
    int test2();

    @RpcCall("method3")
    int test3(int a, int b);
    @RpcCall(value = "method4",async = true)
    Future<String> test4();
}
