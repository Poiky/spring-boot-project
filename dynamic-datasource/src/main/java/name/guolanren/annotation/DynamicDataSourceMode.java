package name.guolanren.annotation;

import java.lang.annotation.*;

/**
 * @author guolanren
 * @date 2019-01-22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSourceMode {

    String DEFAULT = "source1";
    String SOURCE1 = "source1";
    String SOURCE2 = "source2";

    String value() default DEFAULT;
}
