package com.file.mover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.file.mover")
//@EnableSwagger2
public class FileMoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileMoverApplication.class, args);
    }
}