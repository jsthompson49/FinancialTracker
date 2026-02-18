package com.maui.productivity.financial.manager.tag;

import com.maui.productivity.financial.manager.tag.DesignatedDateAmountTagRule.DateAmountForTags;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class DesignatedTransactions {

    private static final List<DateAmountForTags> DESIGNATED_TRANSACTIONS = List.of(
            new DateAmountForTags(-372.0, LocalDate.of(2026, Month.JANUARY, 5), Set.of(Schema.CATEGORY_TRAVEL)),
            new DateAmountForTags(-1628.79, LocalDate.of(2026, Month.JANUARY, 19), Set.of(Schema.CATEGORY_HEALTH)),

            new DateAmountForTags(960.0, LocalDate.of(2026, Month.FEBRUARY, 10), Set.of(Schema.CATEGORY_HEALTH))
            //new DateAmountForTags(1952.76, LocalDate.of(2026, Month.FEBRUARY, 10), Set.of(Schema.CATEGORY_REIMBURSEMENT))
    );

    public static List<DateAmountForTags> getDesignatedTransactions() {
        return DESIGNATED_TRANSACTIONS;
    }
}
