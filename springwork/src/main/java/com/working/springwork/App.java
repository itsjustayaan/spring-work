package com.working.springwork;

import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */

public class App 

{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        String allBeans[] = context.getBeanDefinitionNames();
        
        System.out.println("All beans created:");
        for(String bean:allBeans) {
        	System.out.println(bean);
        }
        
        
        Orders orders = context.getBean("orders", Orders.class);
        
        orders.takeOrders();
        System.out.println(orders.takeLoanOrders());
    }
}
