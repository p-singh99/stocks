package com.demo.stocks.repository;

import com.demo.stocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stock, Integer> {

}
