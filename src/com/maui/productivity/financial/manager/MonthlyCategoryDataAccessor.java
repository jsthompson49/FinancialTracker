package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.DataAccessor;
import com.maui.productivity.financial.model.TaggedTransaction;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MonthlyCategoryDataAccessor implements DataAccessor {

    private final Map<YearMonth, Map<String, List<TaggedTransaction>>> data;

    public MonthlyCategoryDataAccessor(final List<TaggedTransaction> transactions, final TagManager tagManager) {
        this.data = new HashMap<>();
        for (final TaggedTransaction transaction : transactions) {
            final YearMonth month = YearMonth.from(transaction.getTransaction().getDate());
            final String category = tagManager.getTagValue(transaction, Schema.CATEGORY);
            final Map<String, List<TaggedTransaction>> categoryMap = this.data.computeIfAbsent(month, key -> new HashMap<>());
            categoryMap.computeIfAbsent(category, key -> new ArrayList<>()).add(transaction);
        }
    }

    @Override
    public List<TaggedTransaction> getTransactions(final YearMonth month, final String category) {
        return Optional.ofNullable(data.get(month))
                .map(map -> map.get(category))
                .orElse(List.of());
    }
}
