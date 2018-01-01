package pro.leop.princess.server.core.processor.impl;

import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.entity.Response;
import pro.leop.princess.common.parser.IRequestParser;
import pro.leop.princess.common.parser.IResponseParser;
import pro.leop.princess.server.entity.Executer;
import pro.leop.princess.server.core.processor.IProcessor;
import pro.leop.princess.server.core.route.IRoute;

/**
 * 执行器的核心实现
 */
public class CoreProcessor implements IProcessor {
    private final IRequestParser requestParser;
    private final IResponseParser responseParser;
    private final IRoute route;

    /**
     * 需要注入响应的依赖
     *
     * @param requestParser  请求解析器
     * @param responseParser 响应解析器
     * @param route          请求路由
     */
    public CoreProcessor(IRequestParser requestParser, IResponseParser responseParser, IRoute route) {
        this.requestParser = requestParser;
        this.responseParser = responseParser;
        this.route = route;
    }

    @Override
    public byte[] process(byte[] requestData) {
        Request request = requestParser.parse(requestData);
        Executer executer = route.route(request);
        Response response = executer.execute();
        return responseParser.unparse(response);
    }
}
