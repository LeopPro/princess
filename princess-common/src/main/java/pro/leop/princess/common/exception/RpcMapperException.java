package pro.leop.princess.common.exception;

public class RpcMapperException extends RpcException {
    public RpcMapperException() {
    }

    public RpcMapperException(String message) {
        super(message);
    }

    public RpcMapperException(Exception e) {
        super(e);
    }

    public RpcMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
