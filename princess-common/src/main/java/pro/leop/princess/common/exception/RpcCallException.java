package pro.leop.princess.common.exception;

public class RpcCallException extends RpcException {
    public RpcCallException() {
    }

    public RpcCallException(String message) {
        super(message);
    }

    public RpcCallException(Exception e) {
        super(e);
    }

    public RpcCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
