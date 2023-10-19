package com.bvgroup.exchangerates.service;

import com.bvgroup.exchangerates.exceptions.CustomRateException;
import com.bvgroup.exchangerates.util.EnvVarFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExternalRatesServiceImpl implements ExternalRatesService {

    private final RestTemplate restTemplate = new RestTemplate();

    public static final String RATES_ENDPOINT = EnvVarFetcher.getEnvStrElseDefault("RATES_ENDPOINT", "http://api.exchangerate.host/live");
    public static final String ACCESS_KEY = EnvVarFetcher.getEnvStrElseDefault("ACCESS_KEY","b753b41b60860422a27edbcd09505282");

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws Exception, CustomRateException {
        String result = restTemplate.getForObject(RATES_ENDPOINT + "?access_key=" + ACCESS_KEY +
                "&source=" + fromCurrency + "&currencies=" + toCurrency, String.class);

        JSONObject response = new JSONObject(result);

        if (!response.getBoolean("success")) {
            JSONObject errorResponse = response.getJSONObject("error");
            throw new CustomRateException(errorResponse.getString("type"), errorResponse.getString("info"));
        } else {
            if (fromCurrency.equals(toCurrency))
                return BigDecimal.ONE;
        }

        return response.getJSONObject("quotes").getBigDecimal(fromCurrency + toCurrency);
    }

    public HashMap<String, BigDecimal> getExchangeRates(String fromCurrency) throws Exception, CustomRateException {
        String result = restTemplate.getForObject(RATES_ENDPOINT + "?access_key=" + ACCESS_KEY +
                "&source=" + fromCurrency, String.class);

        JSONObject response = new JSONObject(result);

        if (!response.getBoolean("success")) {
            JSONObject errorResponse = response.getJSONObject("error");
            throw new CustomRateException(errorResponse.getString("type"), errorResponse.getString("info"));
        }

        JSONObject responseRates = response.getJSONObject("quotes");
        Iterator<String> keys = responseRates.keys();
        HashMap<String, BigDecimal> exchangeRates = new HashMap<>();

        while(keys.hasNext()) {
            String key = keys.next();
            String toCurrency = key.substring(3);
            BigDecimal exchangeRate = responseRates.getBigDecimal(key);

            exchangeRates.put(toCurrency, exchangeRate);
        }

        return exchangeRates;
    }
}
