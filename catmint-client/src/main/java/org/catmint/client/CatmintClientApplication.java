package org.catmint.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title:Catmint-Client 启动类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@SpringBootApplication
public class CatmintClientApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CatmintClientApplication.class);
        application.run(args);
    }
}
