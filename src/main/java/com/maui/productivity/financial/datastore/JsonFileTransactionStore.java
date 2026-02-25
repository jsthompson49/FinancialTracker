package com.maui.productivity.financial.datastore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.model.TaggedTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class JsonFileTransactionStore implements TransactionStore {

    private final String pathToStore;
    private final ObjectMapper objectMapper;

    @Override
    public List<TaggedTransaction> fetchTransactions() {
        try {
            final Optional<List<TaggedTransaction>> values =
                    Optional.ofNullable(objectMapper.readValue(getFile(), new TypeReference<List<TaggedTransaction>>() {}));
            log.info("Fetched {} transactions from {}", values.map(List::size).orElse(0), pathToStore);
            return values.orElse(Collections.EMPTY_LIST);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public void storeTransactions(List<TaggedTransaction> transactions, final boolean append) {
        try {
            final List<TaggedTransaction> transactionsToWrite = append ? new ArrayList<>(fetchTransactions()) : new ArrayList<>();
            transactionsToWrite.addAll(transactions);

            final List<TaggedTransaction> sortedTransactions = transactionsToWrite.stream()
                    .sorted(Comparator.comparing(TaggedTransaction.DATE_EXTRACTOR).thenComparing(TaggedTransaction.DESCRIPTION_EXTRACTOR))
                    .collect(Collectors.toList());
            objectMapper.writeValue(getFile(), sortedTransactions);
            log.info("Stored {} transactions to {}", sortedTransactions.size(), pathToStore);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private File getFile() {
        return new File(pathToStore);
    }
}
