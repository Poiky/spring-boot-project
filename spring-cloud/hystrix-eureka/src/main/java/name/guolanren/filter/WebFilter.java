package name.guolanren.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author guolanren
 * @date 2019-02-19
 */
@javax.servlet.annotation.WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
@Component
public class WebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            hystrixRequestContext.shutdown();
        }
    }
}
