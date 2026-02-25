package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.parser.TransactionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class ImportManager {
    private final TransactionParser transactionParser;
    private final TagManager tagManager;

    public void importTransactions(final String pathToFile, final TransactionStore transactionStore, final boolean removeDuplicates) {
        try {
            final List<Transaction> parsedTransactions = transactionParser.parse(new FileReader(pathToFile));

            final List<Transaction> newTransactions = removeDuplicates ? removeDuplicates(parsedTransactions, transactionStore) : parsedTransactions;
            log.info("Import transactions: parsed={} new={}", parsedTransactions.size(), newTransactions.size());

            final List<TaggedTransaction> newTaggedTransactions = tagManager.tagTransactions(newTransactions);

            transactionStore.storeTransactions(newTaggedTransactions, true /* append */);
            log.info("Appended {} new transactions", newTaggedTransactions.size());
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private List<Transaction> removeDuplicates(final List<Transaction> transactions, final TransactionStore transactionStore) {
        final Set<Transaction> existingTransactions = transactionStore.fetchTransactions().stream()
                .map(TaggedTransaction::getTransaction)
                .collect(Collectors.toSet());

        return transactions.stream()
                .filter(transaction -> !existingTransactions.contains(transaction))
                .toList();
    }
}
