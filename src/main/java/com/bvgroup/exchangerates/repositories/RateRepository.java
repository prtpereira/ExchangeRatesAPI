package com.bvgroup.exchangerates.repositories;

import com.bvgroup.exchangerates.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    Rate findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}