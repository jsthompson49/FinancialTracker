package com.maui.productivity.financial.report;

import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.manager.MonthlyCategoryDataAccessor;
import com.maui.productivity.financial.manager.TransactionFilters;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class HtmlReportManagerTest {

    private static final YearMonth ANY_MONTH = YearMonth.of(2026, 2);

    private static final String CATEGORY_1 = "cat1";
    private static final String CATEGORY_2 = "cat2";

    @Mock private MonthlyCategoryDataAccessor monthlyDataAccessor;

    private final HtmlReportGenerator reportGenerator = new HtmlReportGenerator();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReportRollup() {
        final List<YearMonth> months = List.of(ANY_MONTH);
        final Set<String> categories = Set.of(CATEGORY_1, CATEGORY_2);

        final List<TaggedTransaction> category1Transactions =
                Artifacts.buildTaggedTransactions(List.of(Artifacts.TEST_DATA_TRANSACTIONS.get(0)));
        final List<TaggedTransaction> category2Transactions =
                Artifacts.buildTaggedTransactions(List.of(Artifacts.TEST_DATA_TRANSACTIONS.get(1)));

        when(monthlyDataAccessor.getTransactions(ANY_MONTH, CATEGORY_1)).thenReturn(category1Transactions);
        when(monthlyDataAccessor.getTransactions(ANY_MONTH, CATEGORY_2)).thenReturn(category2Transactions);

        final String html = reportGenerator.reportRollup(months, monthlyDataAccessor, categories);

        assertThat(html).isNotNull();
        assertThat(html).containsIgnoringWhitespaces("<th>Date</th>");
        assertThat(html).containsIgnoringWhitespaces("<th>Amount</th>");
        assertThat(html).containsIgnoringWhitespaces("<th>Description</th>");
    }
}
