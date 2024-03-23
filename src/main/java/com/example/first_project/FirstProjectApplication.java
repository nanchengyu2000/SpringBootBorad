package com.example.first_project;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import cn.hutool.core.lang.Console;
import com.example.Util.IpAddressUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class FirstProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstProjectApplication.class, args);
    }
    @Bean
    public ApplicationRunner applicationRunner(WebServerApplicationContext context) {
        return (ApplicationArguments args) -> {
            try {
                Console.log("===============项目启动成功 start===============");
                List<String> ipAddressList = IpAddressUtils.getIpAddressOfStartUp(context);
                ipAddressList.forEach(Console::log);
                Console.log("================项目启动成功 end================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
