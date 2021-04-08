package com.turkcell.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AmarisPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmarisPocApplication.class, args).start();
    }

}
