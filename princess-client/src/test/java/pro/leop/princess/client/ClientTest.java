package pro.leop.princess.client;

import org.junit.Test;
import pro.leop.princess.client.reflect.IMapperFactory;
import pro.leop.princess.common.exception.RpcCallException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientTest {
    @Test
    public void test(){
        IMapperFactory mapperFactory = MapperFactorys.getMapperFactory("127.0.0.1", 8008);
        TestMapper testMapper = mapperFactory.getMapper(TestMapper.class);
        try {
            Future<String> future = testMapper.test4();
            System.out.println("异步开始");
            System.out.println(future.get());
        }catch (RpcCallException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            mapperFactory.close();
        }
    }
    @Test
    public void test2(){
//        IMapperFactory mapperFactory = MapperFactorys.getMapperFactory("127.0.0.1", 8008);
//        TestMapper testMapper = mapperFactory.getMapper(TestMapper.class);
//        String s = testMapper.test4();
//        System.out.println(s);

    }
}
