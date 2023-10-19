package com.bvgroup.exchangerates.service;

import com.bvgroup.exchangerates.exceptions.CustomRateException;
import com.bvgroup.exchangerates.models.Rate;
import com.bvgroup.exchangerates.repositories.RateRepository;
import com.bvgroup.exchangerates.util.EnvVarFetcher;
import com.bvgroup.exchangerates.util.ExchangeRateConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class RatesServiceImpl implements RatesService {

    private final RateRepository rateRepository;
    private final ExternalRatesService externalRatesService;

    private static final int REFRESH_INTERNAL_RATES_SECS = EnvVarFetcher.getEnvIntElseDefault("REFRESH_INTERNAL_RATES_SECS", 60);

    @Override
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws Exception, CustomRateException {
        Rate rate = rateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);

        if (rate == null) {
            BigDecimal exchangeRate = externalRatesService.getExchangeRate(fromCurrency, toCurrency);

            Rate newRate = new Rate( null, fromCurrency, toCurrency, ExchangeRateConverter.encode(exchangeRate) );
            saveRate(newRate);

            return ExchangeRateConverter.decode(newRate.getExchangeRate());
        } else {
            long elapsedSeconds = Duration.between(rate.getUpdatedAt(), LocalDateTime.now()).getSeconds();

            if (elapsedSeconds > REFRESH_INTERNAL_RATES_SECS) {
                BigDecimal exchangeRate = externalRatesService.getExchangeRate(fromCurrency, toCurrency);

                rate.setExchangeRate(ExchangeRateConverter.encode(exchangeRate));
                rate.setUpdatedAt(LocalDateTime.now());
                saveRate(rate);
            }
        }

        return ExchangeRateConverter.decode(rate.getExchangeRate());
    }

    @Override
    public HashMap<String, BigDecimal> getExchangeRates(String fromCurrency) throws Exception, CustomRateException {
        HashMap<String, BigDecimal> exchangeRates = externalRatesService.getExchangeRates(fromCurrency);

        exchangeRates.forEach((toCurrency, exchangeRate) -> {
            Rate internalRate = rateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
            LocalDateTime now = LocalDateTime.now();

            if (internalRate == null) {
                saveRate( new Rate(null, fromCurrency, toCurrency, ExchangeRateConverter.encode(exchangeRate), now) );
            } else {
                internalRate.setExchangeRate( ExchangeRateConverter.encode(exchangeRate) );
                internalRate.setUpdatedAt(now);
                rateRepository.save(internalRate);
            }
        });

        return exchangeRates;
    }

    @Override
    public BigDecimal convertAmount(String fromCurrency, String toCurrency, BigDecimal amount) throws Exception, CustomRateException {
        return amount.multiply(getExchangeRate(fromCurrency, toCurrency));
    }

    @Override
    public HashMap<String, BigDecimal> convertAmount(String fromCurrency, List<String> toCurrencies, BigDecimal amount) throws Exception, CustomRateException {
        HashMap<String, BigDecimal> convertedAmounts = new HashMap<>();

        for (String currency: toCurrencies) {
            convertedAmounts.put(currency, convertAmount(fromCurrency, currency, amount));
        }

        return convertedAmounts;
    }
}
