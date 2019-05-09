package cn.gitlab.virtualcry.jcospring.template.boot;

import cn.gitlab.virtualcry.jcospring.extension.context.annotation.JCoComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Server start
 *
 * @author VirtualCry
 */
@JCoComponentScan("cn.gitlab.virtualcry.jcospring.template.handler")
@SpringBootApplication
public class JCoServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(JCoServerBootstrap.class, args);
    }
}
