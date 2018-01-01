package pro.leop.princess.common.exception;

public class RpcMethodNotFoundException extends RpcException {
    public RpcMethodNotFoundException() {
        super();
    }

    public RpcMethodNotFoundException(String message) {
        super(message);
    }
}
