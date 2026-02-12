package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;

import java.util.List;

public class Schema {
    private static final String CATEGORY = "Category";

    private static final String STREAMING_SERVICE = "Entertainment/StreamingService";
    private static final String UTILITIES = "Utilities";

    public static final Tag CATEGORY_UTILITIES = new Tag(CATEGORY, UTILITIES);
    public static final Tag CATEGORY_STREAMING_SERVICE = new Tag(CATEGORY, STREAMING_SERVICE);

    public static List<TagRule> TAG_RULES = List.of(
            new DescriptionPatternTagRule(".*(?i)(NETFLIX).*", CATEGORY_STREAMING_SERVICE),
            new DescriptionPatternTagRule(".*(?i)(ACESOLIDWASTE).*", CATEGORY_UTILITIES)
    );
}
