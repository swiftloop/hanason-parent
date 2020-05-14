package com.framework.hanason.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;

/**
 * @author sorata 2020-03-16 16:01
 */
public class AutoGenerator {
    /**
     * 数据库地址、用户名、密码
     */
    private static final String JDBC_URL = "jdbc:mysql://115.28.130.4:3306/nopa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private static final String PACKAGE = "com.framework.hanason.app";

    public static void main(String[] args) {
        generator();
    }


    private static void generator(){
        com.baomidou.mybatisplus.generator.AutoGenerator generator = new com.baomidou.mybatisplus.generator.AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/hanason-app/src/main/java");
        globalConfig.setAuthor("auto generator");
        //是否打开文件夹
        globalConfig.setOpen(false);
        globalConfig.setEntityName("%sPo");
        globalConfig.setMapperName("%sDao");
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setBaseResultMap(true);
        //直接会覆盖更改的代码
        globalConfig.setFileOverride(false);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setSwagger2(true);


        generator.setGlobalConfig(globalConfig);


        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl(JDBC_URL + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8");
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        dataSourceConfig.setDbType(DbType.MYSQL);

        generator.setDataSource(dataSourceConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE);
        packageConfig.setMapper("dao");
        HashMap<String, String> map = new HashMap<>(4);
        map.put(ConstVal.SERVICE_PATH,globalConfig.getOutputDir()+"/"+packageConfig.getParent().replace(".","/")+"/service");
        map.put(ConstVal.SERVICE_IMPL_PATH,globalConfig.getOutputDir()+"/"+packageConfig.getParent().replace(".","/")+"/service/impl");
        map.put(ConstVal.MAPPER_PATH,globalConfig.getOutputDir()+"/"+packageConfig.getParent().replace(".","/")+"/dao");
        map.put(ConstVal.ENTITY_PATH,globalConfig.getOutputDir()+"/"+packageConfig.getParent().replace(".","/")+"/entity");
        map.put(ConstVal.CONTROLLER_PATH,globalConfig.getOutputDir()+"/"+packageConfig.getParent().replace(".","/")+"/controller");
        map.put(ConstVal.XML_PATH,System.getProperty("user.dir")+"/hanason-app/src/main/resources/mapper");
        packageConfig.setPathInfo(map);

        generator.setPackageInfo(packageConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setEntityBuilderModel(true);
        //仅处理某一些表 填写的是表名
        strategyConfig.setInclude("sys_user");


        generator.setStrategy(strategyConfig);


        InjectionConfig injectionConfig = new InjectionConfig(){
            /**
             * 注入自定义 Map 对象，针对所有表的全局参数
             */
            @Override
            public void initMap() {

            }
        };
        injectionConfig.setFileCreate((configBuilder, fileType, filePath) -> !fileType.equals(FileType.CONTROLLER));

        generator.setCfg(injectionConfig);


        generator.execute();


    }




}
