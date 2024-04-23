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

    private static final String BTC_GET_URL = "https://api.coinbase.com/v2/prices/BTC-USD/buy";

    @Autowired
    private CoinRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws InterruptedException {
        System.out.println("SPRING BOOT APPLICATION STARTED");

        for (int i = 0; i < 100; i++) {
            Coin btc = createBtc(i);
            this.repository.save(btc);
            Thread.sleep(60000);
        }
    }

    private Coin createBtc(long id) {
        ResponseEntity<String> response = restTemplate
                .getForEntity(BTC_GET_URL, String.class);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String amount = documentContext.read("$.data.amount");
        String base = documentContext.read("$.data.base");
        String currency = documentContext.read("$.data.currency");

        Coin btc = new Coin();
        btc.setId(id);
        btc.setName(base);
        btc.setCurrency(currency);
        btc.setAmount(Double.parseDouble(amount));
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }

    private Coin createTestBtc(long id) {
        Coin btc = new Coin();
        btc.setId(id);
        btc.setName("BTC");
        btc.setCurrency("USD");
        btc.setAmount(100000.00);
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }
}
