package com.example.demo.service;

import com.example.demo.annotation.DS;
import com.example.demo.dao.TestTableMapper;
import com.example.demo.model.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestTableMapper testTableMapper;

    @DS("0")
    public void insert(){
        TestTable testTable = new TestTable();
        testTable.setTest("22");
        testTableMapper.insert(testTable);
    }


    @DS("1")
    public String select(){
       return testTableMapper.getTest();
    }

}
