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
public class CoinBaseTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnTheCurrentBTCPrice() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("https://api.coinbase.com/v2/prices/BTC-USD/buy", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String amount = documentContext.read("$.data.amount");
        String base = documentContext.read("$.data.base");
        String currency = documentContext.read("$.data.currency");
        assertThat(amount).isGreaterThan("1.00");
        assertThat(base).isEqualTo("BTC");
        assertThat(currency).isEqualTo("USD");
    }
}
