package pro.leop.princess.client;

import org.junit.Test;
import pro.leop.princess.client.reflect.IMapperFactory;
import pro.leop.princess.common.exception.RpcCallException;

public class ClientTest {
    @Test
    public void test(){
        IMapperFactory mapperFactory = MapperFactorys.getMapperFactory("127.0.0.1", 8008);
        TestMapper testMapper = mapperFactory.getMapper(TestMapper.class);
        try {
            System.out.println(testMapper.test2());
        }catch (RpcCallException e){
            e.printStackTrace();
        }finally {
            mapperFactory.close();
        }
    }
}
