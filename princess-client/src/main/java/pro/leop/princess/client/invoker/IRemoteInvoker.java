package pro.leop.princess.client.invoker;

import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.entity.Response;

public interface IRemoteInvoker {
    Response invoke(Request request);

    void close();
}
