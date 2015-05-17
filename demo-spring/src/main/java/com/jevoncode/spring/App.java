package com.jevoncode.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jevoncode.spring.services.MovieStoreService;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		// create and configure beans
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "services.xml", "daos.xml" });

		// retrieve configured instance
		MovieStoreService service = context.getBean("movieStoreService",
				MovieStoreService.class);

		// use configured instance
		service.createMovie();
		
	}
}
