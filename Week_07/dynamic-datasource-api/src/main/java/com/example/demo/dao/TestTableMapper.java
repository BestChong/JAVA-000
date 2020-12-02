package com.example.demo.dao;

import com.example.demo.model.TestTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestTableMapper {
    int insert(TestTable record);

    String getTest();
}