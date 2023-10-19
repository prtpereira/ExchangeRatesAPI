package com.bvgroup.exchangerates.controllers;

import com.bvgroup.exchangerates.exceptions.CustomRateException;
import com.bvgroup.exchangerates.representers.ConvertAmountsOutputRepresenter;
import com.bvgroup.exchangerates.representers.RateOutputRepresenter;
import com.bvgroup.exchangerates.representers.RatesOutputRepresenter;
import com.bvgroup.exchangerates.service.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RatesController {
    private final RatesService ratesService;

    @GetMapping("/rate")
    public ResponseEntity<Object> getRateExchange(@RequestParam String from, @RequestParam String to) throws Exception {

        BigDecimal rate = ratesService.getExchangeRate(from.toUpperCase(), to.toUpperCase());
        return ResponseEntity.ok().body(new RateOutputRepresenter(from.toUpperCase(), to.toUpperCase(), rate));
    }

    @GetMapping("/rates")
    public ResponseEntity<Object> getRateExchanges(@RequestParam String from) throws Exception {

        HashMap<String, BigDecimal> rates = ratesService.getExchangeRates(from.toUpperCase());
        return ResponseEntity.ok().body(new RatesOutputRepresenter(from.toUpperCase(), rates));
    }

    @GetMapping("/convert")
    public ResponseEntity<Object> convertAmount(@RequestParam String from, @RequestParam List<String> to, @RequestParam String amount) throws Exception {
        BigDecimal parsedAmount;

        try {
            parsedAmount = new BigDecimal(amount);
        } catch (NumberFormatException e) {
            throw new CustomRateException("invalid_number", "'amount' field is not a valid representation of a number.");
        }

        List<String> toCountries = to.stream().map(String::toUpperCase).toList();

        HashMap<String, BigDecimal> convertedAmounts = ratesService.convertAmount(from.toUpperCase(), toCountries, parsedAmount);
        return ResponseEntity.ok().body(new ConvertAmountsOutputRepresenter(from.toUpperCase(), parsedAmount, convertedAmounts));
    }
}