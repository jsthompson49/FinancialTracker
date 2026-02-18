package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.Transaction;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DesignatedDateAmountTagRule implements TagRule {

    private final List<DateAmountForTags> designatedDateAmountForTags;

    public DesignatedDateAmountTagRule(final List<DateAmountForTags> designatedDateAmountForTags) {
        this.designatedDateAmountForTags = designatedDateAmountForTags;
    }

    @Override
    public Set<Tag> apply(Transaction transaction) {
        for (final DateAmountForTags dateAmount : designatedDateAmountForTags) {
            if ((dateAmount.getAmount() == transaction.getAmount()) && (dateAmount.getDate().equals(transaction.getDate()))) {
                return dateAmount.getTags();
            }
        }

        return null;
    }

    @Value
    public static class DateAmountForTags {
        private final double amount;
        private final LocalDate date;
        private final Set<Tag> tags;
    }
}
