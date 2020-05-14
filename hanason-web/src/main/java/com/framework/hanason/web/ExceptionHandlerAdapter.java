package com.framework.hanason.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.framework.hanason.core.domain.ResultData;
import com.framework.hanason.core.exception.RootRuntimeException;
import com.framework.hanason.web.user.AuthUserHolder;
import com.framework.hanason.web.user.IUser;
import com.framework.hanason.web.utils.ParamUtils;
import com.framework.hanason.web.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sorata 2020-05-07 15:03
 * <p>
 * 全局异常处理器
 */

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdapter {


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultData methodNotSupport(HttpServletRequest request,HttpRequestMethodNotSupportedException e) {
        write(request,e);
        return ResultData.err(500,"请求非法");
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class, MissingPathVariableException.class})
    public ResultData notFoundParams(HttpServletRequest request,Exception e){
        write(request,e);
        return ResultData.err(500,"非法参数");
    }


    /**
     * 使用自定义的异常类 必须要自己手动在必要地方使用日志记录
     * @param request HttpServletRequest
     * @param e 抛出的异常
     * @return 返回给app的值
     */
    @ExceptionHandler(value = {RootRuntimeException.class})
    @ResponseBody
    public ResultData customException(HttpServletRequest request,RootRuntimeException e) {
        if (log.isDebugEnabled()){
            write(request,e);
        }
        return ResultData.err(e.getCode(),e.getMsg());
    }


    private static void write(HttpServletRequest request,Exception e){
        String uri = request.getRequestURI();
        Map<String, String> stringStringMap = ParamUtils.readRequestParams(request);
        String ip = WebUtil.getIp(request);
        IUser user = AuthUserHolder.getUser();
        String userId = "NA";
        if (user != null){
            userId = user.getId();
        }
        log.error("接口请求异常,{},{},{},{}",uri, JSON.toJSONString(stringStringMap, SerializerFeature.PrettyFormat),ip,userId);
    }



}
