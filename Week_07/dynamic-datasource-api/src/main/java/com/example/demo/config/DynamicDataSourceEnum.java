package com.example.demo.config;

import lombok.Getter;

@Getter
public enum DynamicDataSourceEnum {

    MASTER(0),
    SLAVA(1);

    private Integer dataSourceCode;

    DynamicDataSourceEnum(Integer dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }
}
