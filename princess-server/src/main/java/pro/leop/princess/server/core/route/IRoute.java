package pro.leop.princess.server.core.route;

import pro.leop.princess.common.entity.Request;
import pro.leop.princess.server.entity.Executer;

/**
 * 请求路由器
 */
public interface IRoute {
    /**
     * 通过request寻找对应方法，并返回其Executer
     * @param request request实体
     * @return Executer执行过程
     */
    Executer route(Request request);
}

