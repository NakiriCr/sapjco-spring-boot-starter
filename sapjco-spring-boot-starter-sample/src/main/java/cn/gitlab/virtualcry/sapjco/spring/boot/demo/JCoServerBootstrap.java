package cn.gitlab.virtualcry.sapjco.spring.boot.demo;

import cn.gitlab.virtualcry.sapjco.spring.context.annotation.JCoComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Server start
 *
 * @author VirtualCry
 */
@JCoComponentScan("cn.gitlab.virtualcry.sapjco.spring.boot.demo.handler")
@SpringBootApplication
public class JCoServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(JCoServerBootstrap.class, args);
    }
}
