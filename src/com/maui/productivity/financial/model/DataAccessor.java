package com.maui.productivity.financial.model;

import java.time.LocalDate;
import java.util.List;

public interface DataAccessor<D> {
    List<TaggedTransaction> getTransactions(final D datePeriod, final String category);

    D getPeridFromDate(final LocalDate localDate);
}
