package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.DataAccessor;
import com.maui.productivity.financial.model.TaggedTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDataAccessor<D> implements DataAccessor<D> {

    private final Map<D, Map<String, List<TaggedTransaction>>> data;

    public AbstractDataAccessor(final List<TaggedTransaction> transactions, final TagManager tagManager) {
        this.data = new HashMap<>();
        for (final TaggedTransaction transaction : transactions) {
            final D datePeriod = getPeridFromDate(transaction.getTransaction().getDate());
            final String category = tagManager.getTagValue(transaction, Schema.CATEGORY);
            final Map<String, List<TaggedTransaction>> categoryMap = this.data.computeIfAbsent(datePeriod, key -> new HashMap<>());
            categoryMap.computeIfAbsent(category, key -> new ArrayList<>()).add(transaction);
        }
    }

    @Override
    public List<TaggedTransaction> getTransactions(final D datePeriod, final String category) {
        return Optional.ofNullable(data.get(datePeriod))
                .map(map -> map.get(category))
                .orElse(List.of());
    }
}
