package la.iit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "la.iit.mapper")
public class OnlineWechatRoomApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineWechatRoomApplication.class, args);
    }

}
