package pro.leop.princess.common.entity;


import pro.leop.princess.common.exception.RpcException;

/**
 * RPC 响应实体
 */
public class Response {

    private String name;
    private Object result;
    private boolean success;
    private String exceptionMessage;

    public Response(){}

    public Response(String name, Object result) {
        this.name = name;
        this.result = result;
        this.success = true;
    }

    public Response(String name, RpcException e) {
        this.name = name;
        this.exceptionMessage = e.getMessage();
        this.success = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }


    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", result=" + result +
                ", success=" + success +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
