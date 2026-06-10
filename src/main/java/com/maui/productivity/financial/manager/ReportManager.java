package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.report.HtmlReportGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class ReportManager {

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance(Locale.US);
    public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    static {
        PERCENT_FORMAT.setMaximumFractionDigits(2);
    }

    private final TagManager tagManager;
    private final HtmlReportGenerator reportGenerator;

    public void listByTag(final String tagName, final List<TaggedTransaction> taggedTransactions) {
        final Map<String, List<TaggedTransaction>> tagValueTransactions = taggedTransactions.stream()
                .collect(Collectors.groupingBy(taggedTransaction -> tagManager.getTagValue(taggedTransaction, tagName)));

        tagValueTransactions.entrySet().forEach(entrySet -> {
            log.info("Category: {}", entrySet.getKey());
            entrySet.getValue().forEach(taggedTransaction -> log.info("     {}", taggedTransaction));
        });
    }

    public void listUndefined(final List<TaggedTransaction> taggedTransactions) {
        final List<TaggedTransaction> undefinedTagTransactions = taggedTransactions.stream()
                .filter(taggedTransaction -> !tagManager.hasTag(taggedTransaction, Schema.CATEGORY))
                .toList();

        log.info("Undfined Category:");
        undefinedTagTransactions.forEach(taggedTransaction -> log.info("{}", taggedTransaction));
    }

    public void reportMonthlyRollup(final List<YearMonth> months, final Set<String> categories,
                                    final List<TaggedTransaction> taggedTransactions) {
        final MonthlyCategoryDataAccessor dataAccessor = new MonthlyCategoryDataAccessor(taggedTransactions, tagManager);

        final String html = reportGenerator.reportRollup(months, dataAccessor, categories);
        writeFile(html, "./target/reports/monthlyRollup.html");
    }

    public void reportMonthlyTotals(final List<YearMonth> months, final Set<String> categories,
                                    final List<TaggedTransaction> taggedTransactions, final YearlyBudget yearlyBudget) {
        final MonthlyCategoryDataAccessor dataAccessor = new MonthlyCategoryDataAccessor(taggedTransactions, tagManager);
        final Map<String, Double> categoryBudget = new HashMap<>();
        yearlyBudget.getAmounts(Year.of(months.get(0).getYear())).entrySet().stream()
                .forEach(entry -> categoryBudget.put(entry.getKey(), entry.getValue() / 12.0d));

        final String html = reportGenerator.reportCategoryTotalsByPeriod(months, dataAccessor, categories, categoryBudget);
        writeFile(html, "./target/reports/monthlyTotals.html");
    }

    public void reportYearlyRollup(final List<Year> years, final Set<String> categories, final List<TaggedTransaction> taggedTransactions) {
        final YearlyCategoryDataAccessor dataAccessor = new YearlyCategoryDataAccessor(taggedTransactions, tagManager);

        final String html = reportGenerator.reportRollup(years, dataAccessor, categories);
        writeFile(html, "./target/reports/yearlyRollup.html");
    }

    public void reportYearlyBudgetProgress(final Year year, final YearlyBudget yearlyBudget, final List<TaggedTransaction> taggedTransactions) {
        final YearlyCategoryDataAccessor dataAccessor = new YearlyCategoryDataAccessor(taggedTransactions, tagManager);

        final LocalDate currentDate = LocalDate.now();
        final double yearDayRatio = (year.getValue() < currentDate.getYear())
                ? 1.0
                : ((double) currentDate.getDayOfYear()) / ((double) year.length());
        log.info("Elapsed days for {} is {}", year, PERCENT_FORMAT.format(yearDayRatio));

        final Map<String, Set<String>> budgetCategories = yearlyBudget.getCategories(year);
        final Map<String, Double> budget = yearlyBudget.getAmounts(year);
        final List<List<String>> dataRows = new ArrayList<>();

        final String MESSAGE_FORMAT = "{}: actual={} budget={}({}) YTD-budget={}({})";
        double total = 0.0;
        double budgetTotal = 0.0;
        for (final String majorCategory : budgetCategories.keySet()) {
            log.info("=====================================================");
            log.info(majorCategory);
            dataRows.add(List.of(majorCategory));
            double majorCategoryTotal = 0.0;
            double budgetMajorCategoryTotal = 0.0;
            for (final String category : budgetCategories.get(majorCategory)) {
                final List<TaggedTransaction> transactions = dataAccessor.getTransactions(year, category);
                final double categoryTotal = transactions.stream()
                        .map(TaggedTransaction::getTransaction)
                        .mapToDouble(Transaction::getAmount)
                        .sum();
                final double budgetCategoryTotal = budget.get(category);

                majorCategoryTotal += categoryTotal;
                budgetMajorCategoryTotal += budgetCategoryTotal;

                final double ytdBudgetCategoryTotal = budgetCategoryTotal * yearDayRatio;
                log.info("  " + MESSAGE_FORMAT, category, CURRENCY_FORMAT.format(-categoryTotal),
                        CURRENCY_FORMAT.format(budgetCategoryTotal), PERCENT_FORMAT.format(-categoryTotal / budgetCategoryTotal),
                        CURRENCY_FORMAT.format(ytdBudgetCategoryTotal), PERCENT_FORMAT.format(-categoryTotal / ytdBudgetCategoryTotal));
                dataRows.add(List.of(category, CURRENCY_FORMAT.format(-categoryTotal),
                        CURRENCY_FORMAT.format(budgetCategoryTotal), PERCENT_FORMAT.format(-categoryTotal / budgetCategoryTotal),
                        CURRENCY_FORMAT.format(ytdBudgetCategoryTotal), PERCENT_FORMAT.format(-categoryTotal / ytdBudgetCategoryTotal)));
            }
            total += majorCategoryTotal;
            budgetTotal += budgetMajorCategoryTotal;

            final double ytdBudgetMajorCategoryTotal = budgetMajorCategoryTotal * yearDayRatio;
            log.info(MESSAGE_FORMAT, majorCategory, CURRENCY_FORMAT.format(-majorCategoryTotal),
                    CURRENCY_FORMAT.format(budgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / budgetMajorCategoryTotal),
                    CURRENCY_FORMAT.format(ytdBudgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / ytdBudgetMajorCategoryTotal));
            dataRows.add(List.of());
            dataRows.add(List.of("Total", CURRENCY_FORMAT.format(-majorCategoryTotal),
                    CURRENCY_FORMAT.format(budgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / budgetMajorCategoryTotal),
                    CURRENCY_FORMAT.format(ytdBudgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / ytdBudgetMajorCategoryTotal)));
        }
        log.info("**********************************************");
        final double ytdBudgetTotal = budgetTotal * yearDayRatio;
        log.info(MESSAGE_FORMAT, year, CURRENCY_FORMAT.format(-total),
                CURRENCY_FORMAT.format(budgetTotal), PERCENT_FORMAT.format(-total / budgetTotal),
                CURRENCY_FORMAT.format(ytdBudgetTotal), PERCENT_FORMAT.format(-total / ytdBudgetTotal));
        dataRows.add(List.of());
        dataRows.add(List.of());
        dataRows.add(List.of("Total - " + year, CURRENCY_FORMAT.format(-total),
                CURRENCY_FORMAT.format(budgetTotal), PERCENT_FORMAT.format(-total / budgetTotal),
                CURRENCY_FORMAT.format(ytdBudgetTotal), PERCENT_FORMAT.format(-total / ytdBudgetTotal)));

        final String html = reportGenerator.reportBudgetProgress("Budget Progress - " + year, dataRows);
        writeFile(html, "./target/reports/budgetProgress.html");
    }

    private void writeFile(final String content, final String pathToFile) {
        try {
            final FileWriter writer = new FileWriter(pathToFile);
            writer.write(content);
            writer.close();
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}

