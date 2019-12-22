package org.catmint;

import org.catmint.io.server.CatmintServerStartupListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author Shuo Xiang
 */
@SpringBootApplication
@ComponentScan({"org.catmint.config"})
public class CatmintServerTestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication( CatmintServerTestApplication.class);
        application.addListeners(new CatmintServerStartupListener());
        application.run(args);
    }
}
