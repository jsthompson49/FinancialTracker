package com.maui.productivity.financial.datastore;

import com.maui.productivity.financial.model.TaggedTransaction;

import java.util.List;

public interface TransactionStore {
    List<TaggedTransaction> fetchTransactions();
    void storeTransactions(final List<TaggedTransaction> transactions, final boolean append);
}
