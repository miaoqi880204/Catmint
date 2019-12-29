package org.catmint.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@SpringBootApplication
@ComponentScan({"org.catmint.client"})
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication( StartApplication.class);
        application.run(args);
    }
}
