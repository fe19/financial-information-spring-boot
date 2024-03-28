package com.project.financialinformationspringboot;

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
                .getForEntity("/BTC-USD/buy", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
