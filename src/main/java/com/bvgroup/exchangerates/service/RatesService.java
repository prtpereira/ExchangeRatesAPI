package com.bvgroup.exchangerates.service;

import com.bvgroup.exchangerates.exceptions.CustomRateException;
import com.bvgroup.exchangerates.models.Rate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface RatesService {

    public Rate saveRate(Rate rate);

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws Exception, CustomRateException;

    public HashMap<String, BigDecimal> getExchangeRates(String fromCurrency) throws Exception, CustomRateException;

    public BigDecimal convertAmount(String fromCurrency, String toCurrency, BigDecimal amount) throws Exception, CustomRateException;

    public HashMap<String, BigDecimal> convertAmount(String fromCurrency, List<String> toCurrencies, BigDecimal amount) throws Exception, CustomRateException;
    }
