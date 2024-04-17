package com.project.financialinformationspringboot.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("https://api.coinbase.com/v2/prices/BTC-USD/buy")
public class RestController {

    @GetMapping("/request")
    private ResponseEntity<String> findById() {
        return ResponseEntity.ok().build();
    }

}
