package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionFiltersTest {

    @Test
    public void testTagTaggedTransactionsFilter() {
        final Predicate<Transaction> dateOnFilter =
                TransactionFilters.getDateOnFilter(LocalDate.of(2026, 1, 29));

        assertThat(dateOnFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(0))).isTrue();
        assertThat(dateOnFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(1))).isFalse();
    }

    @Test
    public void testTagTaggedTransactionsMultipleInRange() {
        final Predicate<Transaction> dateRangeFilter = TransactionFilters.getDateRangeFilter(
                LocalDate.of(2026, 1, 27), LocalDate.of(2026, 3, 2));

        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(0))).isTrue();
        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(1))).isTrue();
    }

    @Test
    public void testTagTaggedTransactionsMultipleNoneInRange() {
        final Predicate<Transaction> dateRangeFilter = TransactionFilters.getDateRangeFilter(
                LocalDate.of(2026, 1, 31), LocalDate.of(2026, 2, 12));

        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(0))).isFalse();
        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(1))).isFalse();
    }

    @Test
    public void testTagTaggedTransactionsInRangeInclusive() {
        final Predicate<Transaction> dateRangeFilter = TransactionFilters.getDateRangeFilter(
                Artifacts.TEST_DATA_TRANSACTIONS.get(0).getDate(), Artifacts.TEST_DATA_TRANSACTIONS.get(1).getDate());

        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(0))).isTrue();
        assertThat(dateRangeFilter.test(Artifacts.TEST_DATA_TRANSACTIONS.get(1))).isTrue();
    }
}
