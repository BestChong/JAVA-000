package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.example.demo.dao")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DataSourceConfig {

    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DynamicDataSourceEnum.MASTER.getDataSourceCode(), masterDataSource());
        dataSourceMap.put(DynamicDataSourceEnum.SLAVA.getDataSourceCode(), slaveDataSource());
        //设置动态数据源
        DynamicDataSourceConfig dynamicDataSource = new DynamicDataSourceConfig();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml");
        factoryBean.setDataSource(dynamicDataSource());
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }






}
