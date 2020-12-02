package com.example.demo.controller;

import com.example.demo.dao.TestTableMapper;
import com.example.demo.model.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
   private TestTableMapper testTableMapper;

    @PostMapping("datasource/test")
    @ResponseBody
    public String test(){
        TestTable testTable = new TestTable();
        testTable.setTest("test");
        testTableMapper.insert(testTable);
        return testTableMapper.getTest();
    }
}
