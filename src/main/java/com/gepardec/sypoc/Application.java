package com.gepardec.sypoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes.
 */
@SpringBootApplication
@PropertySource("${CONFIG_LOCATION_EXTERNAL}")
@ComponentScan(basePackageClasses = Application.class)
@EnableAutoConfiguration
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
