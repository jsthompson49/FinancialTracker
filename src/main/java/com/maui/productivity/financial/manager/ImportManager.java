package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.parser.TransactionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class ImportManager {
    private final TransactionParser transactionParser;
    private final TagManager tagManager;

    public List<TaggedTransaction> importTransactionsDataDryrun(final String dataContent,
                                                                final TransactionStore transactionStore,
                                                                final boolean removeDuplicates) {
        return parseAndTagTransactionsForContent(dataContent, transactionStore, removeDuplicates);
    }

    public List<TaggedTransaction> importTransactionsDryrun(final String pathToFile,
                                                            final TransactionStore transactionStore,
                                                            final boolean removeDuplicates) {
        return parseAndTagTransactions(pathToFile, transactionStore, removeDuplicates);
    }

    public void importTransactions(final String pathToFile, final TransactionStore transactionStore, final boolean removeDuplicates) {
        final List<TaggedTransaction> newTaggedTransactions = parseAndTagTransactions(pathToFile, transactionStore, removeDuplicates);

        transactionStore.storeTransactions(newTaggedTransactions, true /* append */);
        log.info("Appended {} new transactions", newTaggedTransactions.size());
    }

    public void importTransactionsData(final String dataContent, final TransactionStore transactionStore, final boolean removeDuplicates) {
        final List<TaggedTransaction> newTaggedTransactions = parseAndTagTransactionsForContent(dataContent, transactionStore, removeDuplicates);

        transactionStore.storeTransactions(newTaggedTransactions, true /* append */);
        log.info("Appended {} new transactions", newTaggedTransactions.size());
    }

    private List<TaggedTransaction> parseAndTagTransactions(final String pathToFile,
                                                            final TransactionStore transactionStore,
                                                            final boolean removeDuplicates) {
        try {
            final String content = new String(Files.readAllBytes(Paths.get(pathToFile)), StandardCharsets.UTF_8);

            return parseAndTagTransactionsForContent(content, transactionStore, removeDuplicates);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private List<TaggedTransaction> parseAndTagTransactionsForContent(final String content,
                                                                      final TransactionStore transactionStore,
                                                                      final boolean removeDuplicates) {
        try {
            final List<Transaction> parsedTransactions = transactionParser.parse(content);

            final List<Transaction> newTransactions = removeDuplicates ? removeDuplicates(parsedTransactions, transactionStore) : parsedTransactions;
            log.info("Import transactions: parsed={} new={}", parsedTransactions.size(), newTransactions.size());

            return tagManager.tagTransactions(newTransactions);
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
