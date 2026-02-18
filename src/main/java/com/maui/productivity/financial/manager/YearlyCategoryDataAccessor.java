package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.TaggedTransaction;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class YearlyCategoryDataAccessor extends AbstractDataAccessor<Year> {

    public YearlyCategoryDataAccessor(List<TaggedTransaction> transactions, TagManager tagManager) {
        super(transactions, tagManager);
    }

    @Override
    public Year getPeridFromDate(LocalDate localDate) {
        return Year.from(localDate);
    }
}
