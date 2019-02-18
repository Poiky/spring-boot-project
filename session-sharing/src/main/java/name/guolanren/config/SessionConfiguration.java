package name.guolanren.config;

import name.guolanren.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guolanren
 * @date 2019-01-18
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .excludePathPatterns("/user/login")
                .addPathPatterns("/**");
    }
}
