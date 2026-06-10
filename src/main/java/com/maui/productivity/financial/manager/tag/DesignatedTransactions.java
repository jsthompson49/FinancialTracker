package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.manager.tag.DesignatedDateAmountTagRule.DateAmountForTags;
import com.maui.productivity.financial.model.TagRule;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class DesignatedTransactions {

    private static final List<DateAmountForTags> DESIGNATED_TRANSACTIONS = List.of(
            new DateAmountForTags(-372.0, LocalDate.of(2026, Month.JANUARY, 5), Set.of(Schema.CATEGORY_TRAVEL)),
            new DateAmountForTags(-1628.79, LocalDate.of(2026, Month.JANUARY, 19), Set.of(Schema.CATEGORY_HEALTH)),

            new DateAmountForTags(960.0, LocalDate.of(2026, Month.FEBRUARY, 10), Set.of(Schema.CATEGORY_HEALTH)),
            new DateAmountForTags(1952.76, LocalDate.of(2026, Month.FEBRUARY, 10), Set.of(Schema.CATEGORY_TRAVEL)),

            new DateAmountForTags(-30.0, LocalDate.of(2026, Month.MARCH, 2), Set.of(Schema.CATEGORY_SHOPPING)),
            new DateAmountForTags(-403.76, LocalDate.of(2026, Month.MARCH, 19), Set.of(Schema.CATEGORY_SHOPPING)),
            new DateAmountForTags(-1000.0, LocalDate.of(2026, Month.MARCH, 23), Set.of(Schema.CATEGORY_CAR)),
            new DateAmountForTags(-17.26, LocalDate.of(2026, Month.MARCH, 24), Set.of(Schema.CATEGORY_TRAVEL)),
            new DateAmountForTags(-144.0, LocalDate.of(2026, Month.MARCH, 26), Set.of(Schema.CATEGORY_TRAVEL)),
            new DateAmountForTags(2420.0, LocalDate.of(2026, Month.MARCH, 31), Set.of(Schema.CATEGORY_SPORTING_EVENT)),

            new DateAmountForTags(-350.0, LocalDate.of(2026, Month.APRIL, 17), Set.of(Schema.CATEGORY_TRAVEL)),
            new DateAmountForTags(-319.53, LocalDate.of(2026, Month.APRIL, 27), Set.of(Schema.CATEGORY_SHOPPING)),

            new DateAmountForTags(-147.17, LocalDate.of(2026, Month.MAY, 4), Set.of(Schema.CATEGORY_RESTAURANTS)),
            new DateAmountForTags(-975.86, LocalDate.of(2026, Month.MAY, 10), Set.of(Schema.CATEGORY_HOME)),
            new DateAmountForTags(-39.02, LocalDate.of(2026, Month.MAY, 21), Set.of(Schema.CATEGORY_HOME))
    );

    private static final List<TagRule> CHECK_TAG_RULES = List.of(
            new CheckTagRule("4622", Set.of(Schema.CATEGORY_HEALTH)),
            new CheckTagRule("4621", Set.of(Schema.CATEGORY_PROPERTY_TAX)),
            new CheckTagRule("4620", Set.of(Schema.CATEGORY_PROPERTY_TAX))
    );

    public static TagRule getDesignatedTransactions() {
        return new DesignatedDateAmountTagRule(DESIGNATED_TRANSACTIONS);
    }

    public static final List<TagRule> getCheckTagRules() {
        return CHECK_TAG_RULES;
    }
}
