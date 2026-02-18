package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class ReportManager {

    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance(Locale.US);
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    static {
        PERCENT_FORMAT.setMaximumFractionDigits(2);
    }

    private final TagManager tagManager;

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

    public void reportMonthlyRollup(final List<YearMonth> months, final Set<String> categories, final List<TaggedTransaction> taggedTransactions) {
        final MonthlyCategoryDataAccessor dataAccessor = new MonthlyCategoryDataAccessor(taggedTransactions, tagManager);

        double total = 0.0;
        for (final YearMonth month : months) {
            log.info("=====================================================");
            log.info(month);
            double monthTotal = 0.0;
            for (final String category : categories) {
                log.info("*** {}", category);
                final List<TaggedTransaction> transactions = dataAccessor.getTransactions(month, category);
                double categoryTotal = 0.0;
                for (final TaggedTransaction taggedTransaction : transactions) {
                    final Transaction transaction = taggedTransaction.getTransaction();
                    categoryTotal += transaction.getAmount();
                    log.info("    {}: {} - {}", transaction.getDate(), CURRENCY_FORMAT.format(transaction.getAmount()), transaction.getDescription());
                }
                monthTotal += categoryTotal;
                log.info("         Total: {}", CURRENCY_FORMAT.format(categoryTotal));
            }
            total += monthTotal;
            log.info("--------------------------------");
            log.info("Total({}): {}", month, CURRENCY_FORMAT.format(monthTotal));
        }
        log.info("**********************************************");
        log.info("         Total: {}", CURRENCY_FORMAT.format(total));
    }

    public void reportYearlyRollup(final List<Year> years, final Set<String> categories, final List<TaggedTransaction> taggedTransactions) {
        final YearlyCategoryDataAccessor dataAccessor = new YearlyCategoryDataAccessor(taggedTransactions, tagManager);

        double total = 0.0;
        for (final Year year : years) {
            log.info("=====================================================");
            log.info(year);
            double yearTotal = 0.0;
            for (final String category : categories) {
                log.info("*** {}", category);
                final List<TaggedTransaction> transactions = dataAccessor.getTransactions(year, category);
                double categoryTotal = 0.0;
                for (final TaggedTransaction taggedTransaction : transactions) {
                    final Transaction transaction = taggedTransaction.getTransaction();
                    categoryTotal += transaction.getAmount();
                    log.info("    {}: {} - {}", transaction.getDate(), CURRENCY_FORMAT.format(transaction.getAmount()), transaction.getDescription());
                }
                yearTotal += categoryTotal;
                log.info("         Total: {}", CURRENCY_FORMAT.format(categoryTotal));
            }
            total += yearTotal;
            log.info("--------------------------------");
            log.info("Total({}): {}", year, CURRENCY_FORMAT.format(yearTotal));
        }
        log.info("**********************************************");
        log.info("         Total: {}", CURRENCY_FORMAT.format(total));
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

        final String MESSAGE_FORMAT = "{}: actual={} budget={}({}) YTD-budget={}({})";
        double total = 0.0;
        double budgetTotal = 0.0;
        for (final String majorCategory : budgetCategories.keySet()) {
            log.info("=====================================================");
            log.info(majorCategory);
            double majorCategoryTotal = 0.0;
            double budgetMajorCategoryTotal = 0.0;
            for (final String category : budgetCategories.get(majorCategory)) {
                log.info("*** {}", category);
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
            }
            total += majorCategoryTotal;
            budgetTotal += budgetMajorCategoryTotal;

            log.info("--------------------------------");
            final double ytdBudgetMajorCategoryTotal = budgetMajorCategoryTotal * yearDayRatio;
            log.info(MESSAGE_FORMAT, majorCategory, CURRENCY_FORMAT.format(-majorCategoryTotal),
                    CURRENCY_FORMAT.format(budgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / budgetMajorCategoryTotal),
                    CURRENCY_FORMAT.format(ytdBudgetMajorCategoryTotal), PERCENT_FORMAT.format(-majorCategoryTotal / ytdBudgetMajorCategoryTotal));
        }
        log.info("**********************************************");
        final double ytdBudgetTotal = budgetTotal * yearDayRatio;
        log.info(MESSAGE_FORMAT, year, CURRENCY_FORMAT.format(-total),
                CURRENCY_FORMAT.format(budgetTotal), PERCENT_FORMAT.format(-total / budgetTotal),
                CURRENCY_FORMAT.format(ytdBudgetTotal), PERCENT_FORMAT.format(-total / ytdBudgetTotal));
    }
}

