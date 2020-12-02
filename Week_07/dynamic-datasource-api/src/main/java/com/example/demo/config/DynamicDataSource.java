package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;


    @Override
    public void afterPropertiesSet() {
        setDefaultTargetDataSource(masterDataSource);
        Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
        dataSourceMap.put(DynamicDataSourceEnum.MASTER.getDataSourceCode(), masterDataSource);
        dataSourceMap.put(DynamicDataSourceEnum.SLAVA.getDataSourceCode(), slaveDataSource);
        setTargetDataSources(dataSourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Integer dataSourceCode = DataSourceContextHolder.getDataSourceCode();
        if (null == dataSourceCode) {
            return DynamicDataSourceEnum.MASTER.getDataSourceCode();
        }
        return dataSourceCode;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDataSource);
        return factoryBean.getObject();
    }





}
