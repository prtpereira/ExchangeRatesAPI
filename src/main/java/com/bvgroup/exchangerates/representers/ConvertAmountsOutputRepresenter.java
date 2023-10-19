package com.bvgroup.exchangerates.representers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

public class ConvertAmountsOutputRepresenter {
    @JsonProperty("from")
    public String fromCurrency;
    public BigDecimal amount;
    @JsonProperty("converted_amounts")
    public HashMap<String, BigDecimal> convertedAmounts;

    public ConvertAmountsOutputRepresenter(String fromCurrency, BigDecimal amount, HashMap<String, BigDecimal> convertedAmounts) {
        this.fromCurrency = fromCurrency;
        this.amount = amount;
        this.convertedAmounts = convertedAmounts;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public HashMap<String, BigDecimal> getConvertedAmounts() {
        return convertedAmounts;
    }

    public void setConvertedAmounts(HashMap<String, BigDecimal> convertedAmounts) {
        this.convertedAmounts = convertedAmounts;
    }
}
