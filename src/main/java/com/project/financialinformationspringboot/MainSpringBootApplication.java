package com.project.financialinformationspringboot;

import com.project.financialinformationspringboot.data.Coin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MainSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);

    }

    @EventListener(MainSpringBootApplication.class)
    public void runAfterStartup() {
        System.out.println("SPRING BOOT APPLICATION STARTED");
    }

}
