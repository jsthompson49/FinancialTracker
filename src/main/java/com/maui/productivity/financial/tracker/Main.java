package com.maui.productivity.financial.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.datastore.JsonFileTransactionStore;
import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.manager.ReportManager;
import com.maui.productivity.financial.manager.TagManager;
import com.maui.productivity.financial.manager.tag.DesignatedDateAmountTagRule;
import com.maui.productivity.financial.manager.tag.DesignatedTransactions;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.parser.TransactionParser;
import com.maui.productivity.financial.parser.WellsFargoTransactionParser;
import lombok.extern.log4j.Log4j2;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
    private static final ReportManager reportManager = new ReportManager(tagManager);

    public static void main(String[] args) {
        log.info("Hello and welcome!");

        final boolean dryrun = false;
        final boolean importTransactions = false;
        final boolean report = true;

        //final String pathToImportFile = "data/Checking-030126.csv";
        //final String pathToImportFile = "data/CreditCardSecondary-030126.csv";
        final String pathToImportFile = "data/CreditCard-030426.csv";

        try {
            if (dryrun) {
                final boolean removeDuplicates = true;
                final List<TaggedTransaction> taggedTransactions =
                        importManager.importTransactionsDryrun(pathToImportFile, datastore, removeDuplicates);

                reportManager.listByTag(Schema.CATEGORY, taggedTransactions);
                reportManager.listUndefined(taggedTransactions);
            } else {
                if (importTransactions) {
                    importTransactions(pathToImportFile);
                }

                //reapplyTags(true /* replace */);

                if (report) {
                    final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();

                    reportManager.listByTag(Schema.CATEGORY, taggedTransactions);
                    reportManager.listUndefined(taggedTransactions);

                    final List<YearMonth> months = List.of(
                            YearMonth.of(2026, Month.JANUARY),
                            YearMonth.of(2026, Month.FEBRUARY)
                    );

                    reportManager.reportMonthlyRollup(months, yearlyBudget.getAllCatgories(), taggedTransactions);

                    reportManager.reportYearlyRollup(List.of(Year.of(2026)), yearlyBudget.getAllCatgories(), taggedTransactions);

                    reportManager.reportYearlyBudgetProgress(Year.of(2026), yearlyBudget, taggedTransactions);
                }
            }

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
}