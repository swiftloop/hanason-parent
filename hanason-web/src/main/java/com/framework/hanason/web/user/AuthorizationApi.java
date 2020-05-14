package com.framework.hanason.web.user;

import java.lang.annotation.*;

/**
 * @author sorata 2020-03-26 18:17
 *
 * 使用该注解之前，需要得到一个实体对象{@link IUser} 这个是存储在ThreadLocal中的对象，每个线程独立，
 * 在使用前还需要进行修改{@code target} {@link AuthorizationConfig}
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface AuthorizationApi {



}
