package com.bvgroup.exchangerates.representers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RateOutputRepresenter {
    @JsonProperty("from")
    public String fromCurrency;
    @JsonProperty("to")
    public String toCurrency;
    @JsonProperty("exchange_rate")
    public BigDecimal exchangeRate;
    
    public RateOutputRepresenter(String fromCurrency, String toCurrency, BigDecimal exchangeRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
