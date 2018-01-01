package pro.leop.princess.server.test;

import pro.leop.princess.server.annotation.RpcFacade;
import pro.leop.princess.server.annotation.RpcMethod;

@RpcFacade("facade.")
public class RpcTest {
    @RpcMethod("method1")
    public String test1(int val) {
        return String.valueOf(val) + "123";
    }

    @RpcMethod("method2")
    public int test2() {
        return 1 / 0;
    }

    @RpcMethod("method3")
    public int test3(int val1, int val2) {
        return val1 + val2;
    }


}
