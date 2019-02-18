package name.guolanren.aspect;

import name.guolanren.exception.RequestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author guolanren
 * @date 2019-01-22
 */
@Aspect
@Component
public class ParamNonNullAspect {

    public static final Logger LOG = LoggerFactory.getLogger(ParamNonNullAspect.class);

    @Pointcut("execution(public * name.guolanren.service.*.*(..))")
    public void service() {}

    @Before("service()")
    public void beforeService(JoinPoint joinPoint) {
        String[] argNames = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < argNames.length; i++) {
            if (Objects.isNull(args[i])) {
                LOG.error("参数{}不能为NULL", argNames[i]);
                throw new RequestException(String.format("参数%s不能为NULL", argNames[i]));
            }
        }
    }
}
