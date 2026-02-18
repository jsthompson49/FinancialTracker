package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.Transaction;

import java.util.Set;
import java.util.regex.Pattern;

public class DescriptionPatternTagRule implements TagRule {

    private final Pattern pattern;
    private final Set<Tag> tags;

    public DescriptionPatternTagRule(final String expression, final Tag tag) {
        this(expression, Set.of(tag));
    }

    public DescriptionPatternTagRule(final String expression, final Set<Tag> tags) {
        this.pattern = Pattern.compile(expression);
        this.tags = tags;
    }

    @Override
    public Set<Tag> apply(Transaction transaction) {
        return pattern.matcher(transaction.getDescription()).matches() ? tags : null;
    }
}
