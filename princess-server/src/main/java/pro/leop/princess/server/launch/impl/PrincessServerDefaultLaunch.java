package pro.leop.princess.server.launch.impl;

import pro.leop.princess.common.parser.IRequestParser;
import pro.leop.princess.common.parser.IResponseParser;
import pro.leop.princess.common.parser.impl.JsonRequestParser;
import pro.leop.princess.common.parser.impl.JsonResponseParser;
import pro.leop.princess.server.launch.ILaunch;
import pro.leop.princess.server.core.network.IConnectionListioner;
import pro.leop.princess.server.core.network.impl.BioConnectionListioner;
import pro.leop.princess.server.core.processor.IProcessor;
import pro.leop.princess.server.core.processor.impl.CoreProcessor;
import pro.leop.princess.server.core.route.IRoute;
import pro.leop.princess.server.core.route.impl.AnnotationRoute;

/**
 * 默认的启动器
 */
public class PrincessServerDefaultLaunch implements ILaunch {
    private int port;
    private String packagePath;

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    @Override
    public void run() {
        IConnectionListioner connectionListioner = new BioConnectionListioner();
        connectionListioner.setPort(port);
        IRequestParser requestParser = new JsonRequestParser();
        IResponseParser responseParser = new JsonResponseParser();
        IRoute route = new AnnotationRoute(packagePath);
        IProcessor processor = new CoreProcessor(requestParser, responseParser, route);
        connectionListioner.setProcessor(processor);
        connectionListioner.start();
    }
}
