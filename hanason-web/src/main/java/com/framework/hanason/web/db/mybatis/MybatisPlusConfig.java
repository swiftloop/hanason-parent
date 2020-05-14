package com.framework.hanason.web.db.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sorata 2020-05-06 15:00
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnClass(Wrappers.class)
public class MybatisPlusConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 设置最大单页限制数量，默认 200 条，-1 不受限制
        interceptor.setLimit(200);
        //开启 count 的 join 优化,只针对部分 left join
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return interceptor;
    }





}
