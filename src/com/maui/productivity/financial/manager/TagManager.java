package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagManager {

    private static final String UNDEFINED_TAG_NAME = "<Undefined>";
    private final List<TagRule> tagRules;

    public List<TaggedTransaction> tagTransactions(final List<TaggedTransaction> transactions, final boolean replace) {
        return transactions.stream()
                .map(taggedTransaction -> applyRules(taggedTransaction, replace))
                .toList();
    }

    public List<TaggedTransaction> tagTransactions(final List<Transaction> transactions) {
        return transactions.stream()
                .map(this::applyRules)
                .toList();
    }

    public String getTagValue(final TaggedTransaction taggedTransaction, final String tagName) {
        return Optional.ofNullable(taggedTransaction.getTags())
                .orElse(Set.of())
                .stream()
                .filter(tag -> tagName.equals(tag.getName()))
                .findAny()
                .map(Tag::getValue)
                .orElse(UNDEFINED_TAG_NAME);
    }

    private TaggedTransaction applyRules(final TaggedTransaction transaction, final boolean replace) {
        final Set<Tag> tags = computeTags(transaction.getTransaction());

        return new TaggedTransaction(transaction.getTransaction(), replace ? tags : mergeTags(transaction.getTags(), tags));
    }

    private TaggedTransaction applyRules(final Transaction transaction) {
        final Set<Tag> tags = computeTags(transaction);

        return new TaggedTransaction(transaction, tags);
    }

    private Set<Tag> computeTags(final Transaction transaction) {
        final Set<Tag> tags = new HashSet<>();
        tagRules.forEach(tagRule -> {
            final Set<Tag> ruleTags = tagRule.apply(transaction);
            if (ruleTags != null) {
                tags.addAll(ruleTags);
            }
        });

        return tags;
    }

    private Set<Tag> mergeTags(final Set<Tag> existingTags, final Set<Tag> newTags) {
        final Set<Tag> tags = new HashSet<>();
        final Set<String> newTagNames = newTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        existingTags.stream()
                .filter(tag -> !newTagNames.contains(tag.getName()))
                .forEach(tag -> tags.add(tag));
        tags.addAll(newTags);

        return tags;
    }
}
