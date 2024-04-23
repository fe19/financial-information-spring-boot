package com.project.financialinformationspringboot;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.project.financialinformationspringboot.data.Coin;
import com.project.financialinformationspringboot.data.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class MainSpringBootApplication {

    @Autowired
    private CoinRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("SPRING BOOT APPLICATION STARTED");

        Coin testBtc = createTestBtc();
        this.repository.save(testBtc);

        Coin btc = createBtc();
        this.repository.save(btc);
    }

    private Coin createTestBtc() {
        Coin btc = new Coin();
        btc.setId(1L);
        btc.setName("BTC");
        btc.setCurrency("USD");
        btc.setAmount(100000.00);
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }

    private Coin createBtc() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("https://api.coinbase.com/v2/prices/BTC-USD/buy", String.class);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String amount = documentContext.read("$.data.amount");
        String base = documentContext.read("$.data.base");
        String currency = documentContext.read("$.data.currency");

        Coin btc = new Coin();
        btc.setId(2L);
        btc.setName(base);
        btc.setCurrency(currency);
        btc.setAmount(Double.parseDouble(amount));
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }
}
