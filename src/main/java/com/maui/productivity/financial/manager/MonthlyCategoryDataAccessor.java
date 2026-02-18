package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.TaggedTransaction;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class MonthlyCategoryDataAccessor extends AbstractDataAccessor<YearMonth> {

    public MonthlyCategoryDataAccessor(List<TaggedTransaction> transactions, TagManager tagManager) {
        super(transactions, tagManager);
    }

    @Override
    public YearMonth getPeridFromDate(LocalDate localDate) {
        return YearMonth.from(localDate);
    }
}
