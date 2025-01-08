package com.working.springwork;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.working.springwork.Customer.*(..))")
	public void log1() {
		System.out.println("##Logging started");
	}
	
	@After("execution(* com.working.springwork.Customer.payBill(..))")
	public void log2() {
		System.out.println("##Logging done");
	}
	
	@Around("execution(* com.working.springwork.Customer.payBill(..))")
	public void logAround2(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("logAround() is running!");
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("Around before is running!");
		int billAmount = (Integer)( joinPoint.getArgs()[0]);
		if (billAmount > 0) {
			joinPoint.proceed();
		} else {
			System.out.println("Cannot pay the bill as the bill amount is invalid");
		}
		System.out.println("Around after is running!");

		System.out.println("******");

	}
}
