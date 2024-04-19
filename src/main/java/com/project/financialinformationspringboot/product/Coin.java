package com.project.financialinformationspringboot.product;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record Coin(@Id Long id, String name, Double amount, String currency, Date date) {
}
