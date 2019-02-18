package name.guolanren.aspect;

import name.guolanren.annotation.DynamicDataSourceMode;
import name.guolanren.config.DynamicDatasourceConfiguration;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author guolanren
 * @date 2019-01-22
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(dynamicDataSourceMode)")
    public void beforeOperatingDB(DynamicDataSourceMode dynamicDataSourceMode) {
        String mode = dynamicDataSourceMode.value();
        DynamicDatasourceConfiguration.DataSourceContextHolder.setDataSourceKey(mode);
    }

}
