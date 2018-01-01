package pro.leop.princess.server;

import org.junit.Test;
import pro.leop.princess.server.launch.ILaunch;
import pro.leop.princess.server.launch.impl.PrincessServerDefaultLaunch;

public class ServerTest {
    @Test
    public void test(){
        ILaunch launch = new PrincessServerDefaultLaunch();
        launch.setPort(8008);
        launch.setPackagePath("pro.leop.princess.server.test");
        launch.run();
    }
}
