package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;

import java.util.Set;

public class CheckTagRule extends DescriptionPatternTagRule {

    private static final String CHECK_FORMAT = ".*CHECK # %s.*";

    public CheckTagRule(String checkNumber, final Set<Tag> tags) {
        super(String.format(CHECK_FORMAT, checkNumber), tags);
    }
}
