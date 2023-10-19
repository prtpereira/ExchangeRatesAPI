package com.bvgroup.exchangerates.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "fromCurrency", "toCurrency" }) })
public class Rate {
    @Id @GeneratedValue(strategy = AUTO)
    private UUID id;
    @JsonProperty(value = "from_currency")
    private String fromCurrency;
    @JsonProperty(value = "to_currency")
    private String toCurrency;
    @JsonProperty(value = "exchange_rate")
    private BigDecimal exchangeRate;
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;
    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    public Rate() {
        super();
    }

    public Rate(UUID id, String fromCurrency, String toCurrency, BigDecimal exchangeRate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Rate(UUID id, String fromCurrency, String toCurrency, BigDecimal exchangeRate, LocalDateTime createdAt) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public Rate(UUID id, String fromCurrency, String toCurrency, BigDecimal exchangeRate) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Rate(id: " + id + "; from: " + fromCurrency + "; to: " + toCurrency + "; er: " + exchangeRate + ")";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
