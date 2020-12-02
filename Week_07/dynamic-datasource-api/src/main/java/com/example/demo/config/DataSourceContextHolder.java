package com.example.demo.config;

public class DataSourceContextHolder {
    public static final ThreadLocal<Integer> DATASOURCE_CONTEXT = new ThreadLocal<>();

    public static void putDataSource(Integer dataSourceCode) {
        DATASOURCE_CONTEXT.set(dataSourceCode);
    }

    public static Integer getDataSourceCode() {
        return DATASOURCE_CONTEXT.get();
    }

    public static void removeDataSource() {
        DATASOURCE_CONTEXT.remove();
    }
}
