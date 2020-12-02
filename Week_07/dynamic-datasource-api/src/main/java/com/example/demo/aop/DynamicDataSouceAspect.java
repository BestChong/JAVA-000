package com.example.demo.aop;

import com.example.demo.annotation.DS;
import com.example.demo.config.DataSourceContextHolder;
import com.example.demo.config.DynamicDataSourceEnum;
import org.apache.naming.factory.DataSourceLinkFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSouceAspect {

    @Pointcut("@annotation(com.example.demo.annotation.DS)")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object arount(ProceedingJoinPoint joinPoint){
        try {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            String dataSourceType = signature.getMethod().getAnnotation(DS.class).value();
            DataSourceContextHolder.putDataSource(Integer.valueOf(dataSourceType));
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            DataSourceContextHolder.removeDataSource();
        }
        return null;
    }




}
