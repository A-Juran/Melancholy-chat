package la.iit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     */
    public AppTest()
    {
        super(  );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * OkHttp3 request Test:
     */
    @org.junit.jupiter.api.Test
    public void accessWeb() throws IOException {
            //创建okHttp对象。
            OkHttpClient okHttpClient = new OkHttpClient();
            //构建所需要请求的链接、参数等。
            Request build = new Request.Builder().url("https://www.melancholy.cn").build();
            //响应内容打印输出。
            try(Response response = okHttpClient.newCall(build).execute()){
              log.info(response.body().string());
            }
        }

}
