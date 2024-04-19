package com.project.financialinformationspringboot.product;

import org.springframework.data.annotation.Id;

public record Coin(@Id Long id, String name, Double amount, String currency) {
}
