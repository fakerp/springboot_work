package com.sdx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


//开启Servlet组件服务,这样才可以使用filter
@ServletComponentScan
@SpringBootApplication
public class SpringbootWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWorkApplication.class, args);
    }

}
