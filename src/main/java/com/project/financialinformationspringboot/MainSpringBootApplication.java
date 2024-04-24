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
import java.util.List;

@SpringBootApplication
public class MainSpringBootApplication {

    private static final String BTC_GET_URL = "https://api.coinbase.com/v2/prices/BTC-USD/buy";
    private static final int POLLING_TIME_IN_S = 10;
    private static final int NBR_REQUESTS = 100;

    @Autowired
    private CoinRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws InterruptedException {
        System.out.println("SPRING BOOT APPLICATION STARTED");

        long startId = getMaxId() + 1;

        for (long i = 0; i < NBR_REQUESTS; i++) {
            Coin btc = createBtc(i + startId);
            this.repository.save(btc);
            Thread.sleep(1000 * POLLING_TIME_IN_S);
        }
    }

    private Coin createBtc(long id) {
        ResponseEntity<String> response = restTemplate
                .getForEntity(BTC_GET_URL, String.class);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String price = documentContext.read("$.data.amount");
        String base = documentContext.read("$.data.base");
        String currency = documentContext.read("$.data.currency");

        Coin btc = new Coin();
        btc.setId(id);
        btc.setName(base);
        btc.setCurrency(currency);
        btc.setPrice(Double.parseDouble(price));
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }

    private long getMaxId() {
        List<Coin> coins = this.repository.findAll();
        long maxId = 0;
        for (Coin coin : coins) {
            long currentId = coin.getId();
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId;
    }

    private Coin createTestBtc(long id) {
        Coin btc = new Coin();
        btc.setId(id);
        btc.setName("BTC");
        btc.setCurrency("USD");
        btc.setPrice(100000.00);
        btc.setDate(new Date());
        System.out.println("Created a new coin: " + btc);
        return btc;
    }
}
