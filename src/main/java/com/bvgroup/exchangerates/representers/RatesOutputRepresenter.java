package com.bvgroup.exchangerates.representers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class RatesOutputRepresenter {
    @JsonProperty("from")
    public String fromCurrency;
    @JsonProperty("exchange_rates")
    public HashMap exchangeRates;
    
    public RatesOutputRepresenter(String fromCurrency, HashMap exchangeRates) {
        this.fromCurrency = fromCurrency;
        this.exchangeRates = exchangeRates;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public HashMap getexchangeRates() {
        return exchangeRates;
    }

    public void setexchangeRates(HashMap exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
