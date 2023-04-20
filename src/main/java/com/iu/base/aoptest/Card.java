package com.iu.base.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class Card {

	//@Before("execution(* com.iu.base.aoptest.Transport.use*())")
	//@AfterReturning("execution(* com.iu.base.aoptest.Transport.use*())")
	//@Around("execution(* com.iu.base.aoptest.Transport.use*(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable {
		log.error("탑승입니다");
		
		Object [] object = joinPoint.getArgs();
		for(Object obj:object) {
			log.warn("ARGS ====> {}", obj.toString());
		}
		
		
		Object obj = joinPoint.proceed();
		log.error("하차입니다");
		log.warn("Object ===>  {}",obj.toString());
		return obj;
	}
}
