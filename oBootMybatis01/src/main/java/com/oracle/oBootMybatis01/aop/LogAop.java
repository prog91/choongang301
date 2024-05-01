package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// Aop어노테이션 - Aspect
@Aspect
// 순수 Component 등록으로 메모리에 올린다
@Component
public class LogAop {
	// oBootMybatis01.dao.EmpDao 패키지 안에 있는 모든 메소드
	// @Pointcut("within(com.oracle.oBootMybatis01.dao.EmpDao*)")
//	@Pointcut("within(com.oracle.oBootMybatis01.dao.*)")
//	private void pointcutMethod() {
//	}
//	
//	@Around("pointcutMethod()")
//	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
//		// com.oracle.oBootMybatis01.dao.EmpDao 수행되는 핵심관심사 
//		String signatureStr = joinPoint.getSignature().toString();
//		System.out.println(signatureStr +" is Start...");
//		long st = System.currentTimeMillis();
//		
//		
//		try {
//			// 핵심관심사
//			Object obj = joinPoint.proceed();
//			return obj;
//		} finally {
//			long et = System.currentTimeMillis();
//			
//			System.out.println( signatureStr + " is finished.");
//			System.out.println( signatureStr + " 경과시간 : " + (et - st));
//		}	
//	}
//	
//	@Before("pointcutMethod()")
//	public void before() {
//		System.out.println("AOP Before.");
//			
//	}
}
