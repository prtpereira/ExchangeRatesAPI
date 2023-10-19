package com.bvgroup.exchangerates.representers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ConvertAmountOutputRepresenter {
    @JsonProperty("from")
    public String fromCurrency;
    @JsonProperty("to")
    public String toCurrency;
    public BigDecimal amount;
    @JsonProperty("converted_amount")
    public BigDecimal convertedAmount;

    public ConvertAmountOutputRepresenter(String fromCurrency, String toCurrency, BigDecimal amount, BigDecimal convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

}
