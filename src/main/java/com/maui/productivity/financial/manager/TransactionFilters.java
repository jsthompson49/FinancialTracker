package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TransactionFilters {
    public static final Predicate<TaggedTransaction> getFilter(final Predicate<Transaction> transactionFilter) {
        return taggedTransaction -> transactionFilter.test(taggedTransaction.getTransaction());
    }

    public static final Predicate<Transaction> getDateOnFilter(final LocalDate date) {
        return transaction -> transaction.getDate().isEqual(date);
    }

    public static final Predicate<Transaction> getDateRangeFilter(final LocalDate startDateInclusive, final LocalDate endDateInclusive) {
        return transaction -> isInRange(transaction.getDate(), startDateInclusive, endDateInclusive);
    }

    private static final boolean isInRange(final LocalDate dateToCheck, final LocalDate startDateInclusive, final LocalDate endDateInclusive) {
        return (dateToCheck.isEqual(startDateInclusive) || dateToCheck.isAfter(startDateInclusive)) &&
                (dateToCheck.isEqual(endDateInclusive) || dateToCheck.isBefore(endDateInclusive));
    }
}
