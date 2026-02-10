package com.maui.productivity.financial.model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Transaction {
    private final double amount;
    private final LocalDate date;
    private final String description;
}
