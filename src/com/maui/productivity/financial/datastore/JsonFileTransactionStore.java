package com.maui.productivity.financial.datastore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.model.TaggedTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class JsonFileTransactionStore implements TransactionStore {

    private final String pathToStore;
    private final ObjectMapper objectMapper;

    @Override
    public List<TaggedTransaction> fetchTransactions() {
        try {
            final List<TaggedTransaction> values = objectMapper.readValue(getFile(), new TypeReference<List<TaggedTransaction>>() {});
            log.info("Fetched {} transactions from {}", values.size(), pathToStore);
            return values;
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public void storeTransactions(List<TaggedTransaction> transactions, final boolean append) {
        try {
            final List<TaggedTransaction> transactionsToWrite = append ? fetchTransactions() : new ArrayList<>();
            transactionsToWrite.addAll(transactions);
            objectMapper.writeValue(getFile(), transactionsToWrite);
            log.info("Stored {} transactions to {}", transactionsToWrite.size(), pathToStore);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private File getFile() {
        return new File(pathToStore);
    }
}
