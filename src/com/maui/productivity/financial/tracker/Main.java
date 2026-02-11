package com.maui.productivity.financial.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.datastore.JsonFileTransactionStore;
import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.parser.TransactionParser;
import com.maui.productivity.financial.parser.WellsFargoTransactionParser;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    private static final String PATH_TO_TRANSACTIONS_JSON_DATASTORE = "target/datastore/transactions.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TransactionStore datastore = new JsonFileTransactionStore(PATH_TO_TRANSACTIONS_JSON_DATASTORE, objectMapper);

    private static final TransactionParser transactionParser = new WellsFargoTransactionParser();
    private static final ImportManager importManager = new ImportManager(transactionParser);

    static {
        objectMapper.findAndRegisterModules();
    }

    public static void main(String[] args) {
        log.info("Hello and welcome!");

        try {
            importTransactions("data/Checking2.csv");
            //importTransactions("data/CreditCard4.csv");

            //datastore.storeTransactions(taggedTransactions);
            //log.info("Successfully store transactions at {}", PATH_TO_TRANSACTIONS_JSON_DATASTORE);
        } catch (final Exception e) {
            log.error("Error in program", e);
        }
    }

    private static void importTransactions(final String pathToFile) {
        importManager.importTransactions(pathToFile, datastore, true);
    }
}