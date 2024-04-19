package com.project.financialinformationspringboot.data;

import com.project.financialinformationspringboot.product.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long> {
}
