package com.maui.productivity.financial.tracker;

import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.parser.WellsFargoTransactionParser;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.util.Set;

@Log4j2
public class Main {
    public static void main(String[] args) {
        log.info("Hello and welcome!");

        try {
            final FileReader fileReader = new FileReader("data/CreditCard4.csv");
            final Set<Transaction> transactions = new WellsFargoTransactionParser().parse(fileReader);
            log.info("Parsed Transactions: {}", transactions);
        } catch (final Exception e) {
            log.error("Error in program", e);
        }
    }
}