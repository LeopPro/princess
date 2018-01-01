package pro.leop.princess.common.exception;

public class RpcException extends RuntimeException {
    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(Exception e) {
        super(e);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
}