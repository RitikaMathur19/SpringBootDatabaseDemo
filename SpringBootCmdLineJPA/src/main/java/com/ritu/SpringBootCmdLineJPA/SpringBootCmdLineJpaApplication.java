package com.ritu.SpringBootCmdLineJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/***
 * Displaying the Use CommandLine runner interface in Spring Boot If there are
 * multiple command line interface classes the we can specify order in which
 * they should be called by specifying @Order(value="some int") annotation First
 * class to start is the one with @SpringBootApplication but its run method is
 * not executed if there is another class with order value as 1
 * After @Order(value="1") is executed then other order is executed else next
 * the @SpringBootApplication class is executed
 * 
 * Pending - main can call a bean
 * 
 * @author Ritika
 *
 */
//@Order(value=3)

@SpringBootApplication
public class SpringBootCmdLineJpaApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootCmdLineJpaApplication.class);

	// the following (@Bean) can also be used to create another class .
	// For this class so not use @Component if it is to be generated through @Bean
	// can be accessed outside package

	/*
	 * @Bean public InputDataCmdLineRunner inputDataRunner() { return new
	 * InputDataCmdLineRunner();
	 * 
	 * }
	 */

	public static void main(String[] args) {
		// following is the first line to get printed but run() is called later
		log.info("Spring Boot Main application started...");
		// Spring Boot run method returns an application context object
		ApplicationContext appctx = SpringApplication.run(SpringBootCmdLineJpaApplication.class, args);
		log.info("Application name : " + appctx.getApplicationName());
		log.info("No of beans configured : " + appctx.getBeanDefinitionCount());
		log.info("----------Bean Names-------- ");
		for (String context : appctx.getBeanDefinitionNames()) {
			log.info("***" + context + "***");

		}

	}

	@Override
	public void run(String... args) {

		log.info("Start Main Application...");

	}

}