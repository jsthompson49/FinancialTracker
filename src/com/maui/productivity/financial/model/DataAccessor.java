package com.maui.productivity.financial.model;

import java.time.YearMonth;
import java.util.List;

public interface DataAccessor {
    List<TaggedTransaction> getTransactions(final YearMonth month, final String category);
}
