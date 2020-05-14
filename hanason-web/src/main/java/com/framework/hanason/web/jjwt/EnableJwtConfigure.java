package com.framework.hanason.web.jjwt;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author sorata 2020-05-06 10:24
 *
 * 启用jwt配置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JwtAutoConfigure.class)
public @interface EnableJwtConfigure {
}
