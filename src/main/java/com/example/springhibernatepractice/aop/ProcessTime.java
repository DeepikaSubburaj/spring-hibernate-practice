package com.example.springhibernatepractice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ProcessTime {


    @Before("execution(* com.example.springhibernatepractice.controller.WordSearch.*.*(..))  ")
    public void beforeAdvice(JoinPoint joinPoint ) {
        //Advice
        System.out.println("Entering method:" + joinPoint.getSignature().getName());
        //System.out.println("Searching in the file: " + fileName );
        System.out.println("Search start time: " + System.currentTimeMillis() );
    }



}
