package name.guolanren.interceptor;

import name.guolanren.exception.SessionException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @autoro guolanren
 * @date 2019-01-18
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user"))) {
            return true;
        }
        throw new SessionException();
    }

}
