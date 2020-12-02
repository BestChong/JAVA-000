package com.example.demo.controller;


import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @Autowired
   private TestService testService;

    @PostMapping("datasource/test")
    @ResponseBody
    public String test(){
        testService.insert();
        return testService.select();
    }
}
