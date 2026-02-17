package com.maui.productivity.financial.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.datastore.JsonFileTransactionStore;
import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.manager.MonthlyCategoryDataAccessor;
import com.maui.productivity.financial.manager.TagManager;
import com.maui.productivity.financial.manager.YearlyCategoryDataAccessor;
import com.maui.productivity.financial.manager.tag.DesignatedDateAmountTagRule;
import com.maui.productivity.financial.manager.tag.DesignatedTransactions;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.parser.TransactionParser;
import com.maui.productivity.financial.parser.WellsFargoTransactionParser;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class Main {
    private static final String PATH_TO_TRANSACTIONS_JSON_DATASTORE = "target/datastore/transactions.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TransactionStore datastore = new JsonFileTransactionStore(PATH_TO_TRANSACTIONS_JSON_DATASTORE, objectMapper);

    private static final List<TagRule> tagRules = new ArrayList<>();

    static {
        objectMapper.findAndRegisterModules();

        tagRules.addAll(Schema.TAG_RULES);
        tagRules.add(new DesignatedDateAmountTagRule(DesignatedTransactions.getDesignatedTransactions()));
    }

    private static final TransactionParser transactionParser = new WellsFargoTransactionParser();
    private static final TagManager tagManager = new TagManager(tagRules);
    private static final ImportManager importManager = new ImportManager(transactionParser, tagManager);
    private static final YearlyBudget yearlyBudget = new YearlyBudget();

    public static void main(String[] args) {
        log.info("Hello and welcome!");

        try {
            //importTransactions("data/Checking-021426.csv");
            //importTransactions("data/CreditCard-021426.csv");

            //reapplyTags(true /* replace */);

            //listByTag(Schema.CATEGORY);
            listUndefined(Schema.CATEGORY);

            reportMonthlyRollup(
                    List.of(
                            YearMonth.of(2026, Month.JANUARY),
                            YearMonth.of(2026, Month.FEBRUARY)
                    ),
                    yearlyBudget.getAllCatgories()
            );

            reportYearlyRollup(List.of(Year.of(2026)), yearlyBudget.getAllCatgories());

            reportYearlyBudgetProgress(Year.of(2026), yearlyBudget);

            log.info("Completed successfully");
        } catch (final Exception e) {
            log.error("Error in program", e);
        }
    }

    private static void importTransactions(final String pathToFile) {
        importManager.importTransactions(pathToFile, datastore, true);
    }

    private static void reapplyTags(final boolean replace) {
        final List<TaggedTransaction> transactions = datastore.fetchTransactions();
        final List<TaggedTransaction> newTransactions = tagManager.tagTransactions(transactions, replace);
        datastore.storeTransactions(newTransactions, false);
    }

    private static void listByTag(final String tagName) {
        final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();

        final Map<String, List<TaggedTransaction>> tagValueTransactions = taggedTransactions.stream()
                .collect(Collectors.groupingBy(taggedTransaction -> tagManager.getTagValue(taggedTransaction, tagName)));

        tagValueTransactions.entrySet().forEach(entrySet -> {
            log.info("Category: {}", entrySet.getKey());
            entrySet.getValue().forEach(taggedTransaction -> log.info("     {}", taggedTransaction));
        });
    }

    private static void listUndefined(final String tagName) {
        final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();

        final List<TaggedTransaction> undefinedTagTransactions = taggedTransactions.stream()
                .filter(taggedTransaction -> !tagManager.hasTag(taggedTransaction, Schema.CATEGORY))
                .toList();

        log.info("Undfined Category:");
        undefinedTagTransactions.forEach(taggedTransaction -> log.info("{}", taggedTransaction));
    }

    private static void reportMonthlyRollup(final List<YearMonth> months, final Set<String> categories) {
        final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();
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
                    log.info("    {}: {} - {}", transaction.getDate(), transaction.getAmount(), transaction.getDescription());
                }
                monthTotal += categoryTotal;
                log.info("         Total: {}", categoryTotal);
            }
            total += monthTotal;
            log.info("--------------------------------");
            log.info("Total({}): {}", month, monthTotal);
        }
        log.info("**********************************************");
        log.info("         Total: {}", total);
    }

    private static void reportYearlyRollup(final List<Year> years, final Set<String> categories) {
        final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();
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
                    log.info("    {}: {} - {}", transaction.getDate(), transaction.getAmount(), transaction.getDescription());
                }
                yearTotal += categoryTotal;
                log.info("         Total: {}", categoryTotal);
            }
            total += yearTotal;
            log.info("--------------------------------");
            log.info("Total({}): {}", year, yearTotal);
        }
        log.info("**********************************************");
        log.info("         Total: {}", total);
    }

    private static void reportYearlyBudgetProgress(final Year year, final YearlyBudget yearlyBudget) {
        final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();
        final YearlyCategoryDataAccessor dataAccessor = new YearlyCategoryDataAccessor(taggedTransactions, tagManager);

        final LocalDate currentDate = LocalDate.now();
        final double yearDayRatio = (year.getValue() < currentDate.getYear())
                ? 1.0
                : ((double) currentDate.getDayOfYear()) / ((double) year.length());
        log.info("Elapsed days for {} is {}", year, yearDayRatio);

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
                log.info("  " + MESSAGE_FORMAT, category, -categoryTotal,
                        budgetCategoryTotal, -categoryTotal / budgetCategoryTotal, ytdBudgetCategoryTotal, -categoryTotal /ytdBudgetCategoryTotal);
            }
            total += majorCategoryTotal;
            budgetTotal += budgetMajorCategoryTotal;

            log.info("--------------------------------");
            final double ytdBudgetMajorCategoryTotal = budgetMajorCategoryTotal * yearDayRatio;
            log.info(MESSAGE_FORMAT, majorCategory, -majorCategoryTotal,
                    budgetMajorCategoryTotal, -majorCategoryTotal / budgetMajorCategoryTotal,
                    ytdBudgetMajorCategoryTotal, -majorCategoryTotal / ytdBudgetMajorCategoryTotal);
        }
        log.info("**********************************************");
        final double ytdBudgetTotal = budgetTotal * yearDayRatio;
        log.info(MESSAGE_FORMAT, year, -total, budgetTotal, -total / budgetTotal, ytdBudgetTotal, -total / ytdBudgetTotal);
    }
}