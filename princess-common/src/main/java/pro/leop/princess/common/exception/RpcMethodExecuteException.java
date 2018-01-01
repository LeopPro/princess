package pro.leop.princess.common.exception;

public class RpcMethodExecuteException extends RpcException {
    public RpcMethodExecuteException() {
    }

    public RpcMethodExecuteException(String message) {
        super(message);
    }

    public RpcMethodExecuteException(Exception e) {
        super(e);
    }

    public RpcMethodExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
