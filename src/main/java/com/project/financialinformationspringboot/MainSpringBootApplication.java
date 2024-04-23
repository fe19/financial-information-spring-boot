package com.project.financialinformationspringboot;

import com.project.financialinformationspringboot.data.Coin;
import com.project.financialinformationspringboot.data.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MainSpringBootApplication {

    @Autowired
    private CoinRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("SPRING BOOT APPLICATION STARTED");
        Coin btc = createBtcCoin();
        this.repository.save(btc);
    }

    private Coin createBtcCoin() {
        Coin btc = new Coin();
        btc.setId(1L);
        btc.setName("BTC");
        btc.setCurrency("USD");
        btc.setAmount(100000.00);
        System.out.println("Created a new coin: " + btc);
        return btc;
    }
}
