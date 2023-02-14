package la.iit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author JuRan
 * @date 2023/1/20
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxAppIdConfig {
    /**
     * 小程序Id
     */
    private String appid;

    /**
     * 小程序密钥
     */
    private String secret;

}
