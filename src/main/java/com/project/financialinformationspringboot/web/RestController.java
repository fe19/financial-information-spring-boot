package com.project.financialinformationspringboot.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("https://api.coinbase.com/v2/prices")
public class RestController {

    @GetMapping("/BTC-USD/buy")
    private ResponseEntity<String> findById() {
        return ResponseEntity.ok("100000.00");
    }

}
