package com.project.financialinformationspringboot;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinRecordBaseTest {

    @Autowired
    TestRestTemplate restTemplate;

    private static final String URL_BTC = "https://api.coinbase.com/v2/prices/BTC-USD/buy";

    @Test
    void shouldReturnATestBTCPrice() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/test/api.coinbase.com/v2/prices/BTC-USD/buy", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        System.out.println("OUTPUT = " + documentContext.jsonString());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        Double price = documentContext.read("$.price");
        String currency = documentContext.read("$.currency");

        assertThat(id).isEqualTo(1);
        assertThat(name).isEqualTo("BTC");
        assertThat(price).isEqualTo(100000.00);
        assertThat(currency).isEqualTo("USD");
    }

    @Test
    void shouldReturnTheCurrentBTCPrice() {
        ResponseEntity<String> response = restTemplate
                .getForEntity(URL_BTC, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String amount = documentContext.read("$.data.amount");
        String base = documentContext.read("$.data.base");
        String currency = documentContext.read("$.data.currency");

        assertThat(amount).isGreaterThan("1.00");
        assertThat(base).isEqualTo("BTC");
        assertThat(currency).isEqualTo("USD");
    }

    @Test
    void shouldStoreTheCurrentBTCPriceInTheDb() {
        ResponseEntity<String> response = restTemplate
                .getForEntity(URL_BTC, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
    }
}
