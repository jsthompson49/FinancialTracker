package com.maui.productivity.financial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class Transaction {
    private final double amount;
    private final LocalDate date;
    private final String description;

    @JsonCreator
    public Transaction(@JsonProperty("amount") double amount, @JsonProperty("date") LocalDate date, @JsonProperty("description") String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}
