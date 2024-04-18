package com.project.financialinformationspringboot.web;

import com.project.financialinformationspringboot.product.Coin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("https://api.coinbase.com/v2/prices")
public class RestController {

    @GetMapping("/BTC-USD/buy")
    private ResponseEntity<Coin> findById() {
        Coin coin = new Coin(1L, 100000.00);
        return ResponseEntity.ok(coin);
    }

}
