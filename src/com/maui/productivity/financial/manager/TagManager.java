package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class TagManager {

    private final List<TagRule> tagRules;

    public List<TaggedTransaction> tagTransactions(final List<Transaction> transactions) {
        return transactions.stream()
                .map(this::applyRules)
                .toList();
    }

    private TaggedTransaction applyRules(final Transaction transaction) {
        final Set<Tag> tags = new HashSet<>();
        tagRules.forEach(tagRule -> {
            final Set<Tag> ruleTags = tagRule.apply(transaction);
            if (ruleTags != null) {
                tags.addAll(ruleTags);
            }
        });

        return new TaggedTransaction(transaction, tags);
    }

}
