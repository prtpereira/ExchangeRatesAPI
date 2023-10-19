package com.bvgroup.exchangerates.util;

import java.math.BigDecimal;

public class ExchangeRateConverter {
    private static final int EXCHANGE_RATE_MULTIPLIER = 100_000;

    public static BigDecimal decode(BigDecimal longExchangeRate) {
        return longExchangeRate.divide(BigDecimal.valueOf(EXCHANGE_RATE_MULTIPLIER));
    }

    public static BigDecimal encode(BigDecimal exchangeRate) {
        return exchangeRate.multiply(BigDecimal.valueOf(EXCHANGE_RATE_MULTIPLIER));
    }

}
