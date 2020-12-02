package com.example.demo.config;


import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Component
public class DynamicDataSourceConfig extends AbstractRoutingDataSource {

    @Autowired
    private DataSource masterDataSource;
    @Autowired
    private DataSource slaveDataSource;



    @Override
    protected Object determineCurrentLookupKey() {
        Integer dataSourceCode = DataSourceContextHolder.getDataSourceCode();
        if (null == dataSourceCode) {
            return DynamicDataSourceEnum.MASTER.getDataSourceCode();
        }
        return dataSourceCode;
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DynamicDataSourceEnum.MASTER.getDataSourceCode(), masterDataSource);
        dataSourceMap.put(DynamicDataSourceEnum.SLAVA.getDataSourceCode(), slaveDataSource);
        setTargetDataSources(dataSourceMap);
        setDefaultTargetDataSource(masterDataSource);
        super.afterPropertiesSet();
    }
}
