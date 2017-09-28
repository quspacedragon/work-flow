package com.quspacedragon.workflow;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableWebMvc
@SpringBootApplication
@ImportResource({"classpath:conf/*.xml"})
@MapperScan(basePackages = "com.quspacedragon.workflow.mapper")
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner, EmbeddedServletContainerCustomizer {
    private Logger logger = LoggerFactory.getLogger(Application.class);
    private static int port = 8080;

    public static void main(String[] args) {
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("服务启动完成!");
    }

    @RequestMapping("/")
    String home() {
        return "redirect:countries";
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(port);
    }
}
