package com.framework.hanason.web.user;

import com.framework.hanason.core.enums.ErrorCodeEnum;
import com.framework.hanason.core.exception.ServerException;
import com.framework.hanason.web.jjwt.JwtTemplate;
import com.framework.hanason.web.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sorata 2020-05-07 17:37
 */
@Slf4j
public class AuthorizationApiMethodInterceptor<T extends IUser> implements MethodInterceptor {

    /**
     * 接口携带的请求头 根据源码 无需在意大小写
     */
    private static final String HEADER_KEY = "AUTHORIZATION";

    private JwtTemplate jwtTemplate;

    private Class<T> target;

    AuthorizationApiMethodInterceptor(JwtTemplate template, Class<T> target){
        this.jwtTemplate = template;
        this.target = target;
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //执行前拦截
        HttpServletRequest request = WebUtil.getRequest();
        if (request == null){
            if (log.isDebugEnabled()){
                log.debug("不能获取到request,{}",methodInvocation.getArguments());
            }
            throw new ServerException(ErrorCodeEnum.NOT_FOUND_REQUEST);
        }
        String header = request.getHeader(HEADER_KEY);
        if (StringUtils.isEmpty(header)){
            if (log.isDebugEnabled()){
                log.debug("request中不包含请求头{}",HEADER_KEY);
            }
            throw new ServerException(ErrorCodeEnum.NOT_FOUND_AUTH_HEADER);
        }
        try {
            T verify = jwtTemplate.verify(header, target);
            AuthUserHolder.setUser(verify);
        } catch (Exception e) {
            log.error("token验证失败,接口: {}, ip: {}",request.getRequestURI(),WebUtil.getIp(request));
           throw new ServerException(ErrorCodeEnum.TOKEN_VALID_ERROR);
        }
        try {
            return methodInvocation.proceed();
        } finally {
            AuthUserHolder.remove();
        }
    }
}
