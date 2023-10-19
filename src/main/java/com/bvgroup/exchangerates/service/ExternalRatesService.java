package com.bvgroup.exchangerates.service;

import com.bvgroup.exchangerates.exceptions.CustomRateException;

import java.math.BigDecimal;
import java.util.HashMap;

public interface ExternalRatesService {

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws Exception, CustomRateException;

    public HashMap<String, BigDecimal> getExchangeRates(String fromCurrency) throws Exception, CustomRateException;
}
