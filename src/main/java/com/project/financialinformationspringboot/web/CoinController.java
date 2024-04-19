package com.project.financialinformationspringboot.web;

import com.project.financialinformationspringboot.data.CoinRepository;
import com.project.financialinformationspringboot.product.Coin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("")
public class CoinController {

    private final CoinRepository coinRepository;

    private CoinController(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @GetMapping("https://api.coinbase.com/v2/prices/BTC-USD/buy")
    private ResponseEntity<Coin> findById() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/api.coinbase.com/v2/prices/BTC-USD/buy")
    private ResponseEntity<Coin> getBtc() {
        Optional<Coin> coinOptional =
                coinRepository.findById(1L);
        if (coinOptional.isPresent()){
            return ResponseEntity.ok(coinOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
