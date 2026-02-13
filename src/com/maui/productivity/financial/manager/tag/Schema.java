package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;

import java.util.List;

public class Schema {
    public static final String CATEGORY = "Category";

    private static final String DONATION = "Donation";
    private static final String INTEREST = "Income/Interest";
    private static final String STREAMING_SERVICE = "Entertainment/StreamingService";
    private static final String UTILITIES = "Utilities";

    public static final Tag CATEGORY_DONATION = new Tag(CATEGORY, DONATION);
    public static final Tag CATEGORY_INTEREST = new Tag(CATEGORY, INTEREST);
    public static final Tag CATEGORY_STREAMING_SERVICE = new Tag(CATEGORY, STREAMING_SERVICE);
    public static final Tag CATEGORY_UTILITIES = new Tag(CATEGORY, UTILITIES);

    public static List<TagRule> TAG_RULES = List.of(
            new DescriptionPatternTagRule(".*(?i)(ACESOLIDWASTE).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(CITY OF SHOREVIE UTILITIES).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(COMCAST).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(INTEREST PAYMENT).*", CATEGORY_INTEREST),
            new DescriptionPatternTagRule(".*(?i)(MN Pub Radio).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(NETFLIX).*", CATEGORY_STREAMING_SERVICE),
            new DescriptionPatternTagRule(".*(?i)(UNBOUND DONATION).*", CATEGORY_DONATION),
            new DescriptionPatternTagRule(".*(?i)(VERIZON WIRELESS PAYMENTS).*", CATEGORY_UTILITIES),
            new DescriptionPatternTagRule(".*(?i)(XCELENERGY).*", CATEGORY_UTILITIES)
    );
}
