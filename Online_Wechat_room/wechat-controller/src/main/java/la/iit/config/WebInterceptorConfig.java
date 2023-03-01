package la.iit.config;

import la.iit.security.filter.VisitLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JuRan
 * @date 2023/3/1
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    private VisitLimitInterceptor visitLimitInterceptor;
    public WebInterceptorConfig(VisitLimitInterceptor visitLimitInterceptor){
        this.visitLimitInterceptor = visitLimitInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitLimitInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
