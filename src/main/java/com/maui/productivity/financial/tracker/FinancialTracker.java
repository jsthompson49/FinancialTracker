package com.maui.productivity.financial.tracker;

import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.manager.ReportManager;
import com.maui.productivity.financial.manager.TagManager;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TaggedTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Log4j2
@RequiredArgsConstructor
public class FinancialTracker {
    private final TransactionStore datastore;
    private final TagManager tagManager;
    private final ImportManager importManager;
    private final YearlyBudget yearlyBudget;
    private final ReportManager reportManager;

    public void execute(final boolean dryrun,
                        final boolean importTransactions,
                        final boolean report,
                        final Predicate<TaggedTransaction> reapplyFilter,
                        final String pathToImportFile) {
        log.info("Hello and welcome!");

        log.info("Import File: {}", pathToImportFile);
        log.info("Options: dryrun={} import={} report={}", dryrun, importTransactions, report);

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

            if (reapplyFilter != null) {
                reapplyTags(true /* replace */, reapplyFilter);
            }

            if (report) {
                final List<TaggedTransaction> taggedTransactions = datastore.fetchTransactions();

                reportManager.listByTag(Schema.CATEGORY, taggedTransactions);
                reportManager.listUndefined(taggedTransactions);

                final List<YearMonth> months = List.of(
                        YearMonth.of(2026, Month.JANUARY),
                        YearMonth.of(2026, Month.FEBRUARY),
                        YearMonth.of(2026, Month.MARCH),
                        YearMonth.of(2026, Month.APRIL),
                        YearMonth.of(2026, Month.MAY)
                );

                final Set<String> yearlyCategories = yearlyBudget.getAllCategories();

                reportManager.reportMonthlyRollup(months, yearlyCategories, taggedTransactions);

                reportManager.reportMonthlyTotals(months, yearlyCategories, taggedTransactions, yearlyBudget);

                reportManager.reportYearlyRollup(List.of(Year.of(2026)), yearlyCategories, taggedTransactions);

                reportManager.reportYearlyBudgetProgress(Year.of(2026), yearlyBudget, taggedTransactions);
            }
        }

        log.info("Completed successfully");
    }

    private void importTransactions(final String pathToFile) {
        importManager.importTransactions(pathToFile, datastore, true);
    }

    private void reapplyTags(final boolean replaceTags, final Predicate<TaggedTransaction> applyFilter) {
        final List<TaggedTransaction> transactions = datastore.fetchTransactions();
        final List<TaggedTransaction> newTransactions = tagManager.tagTransactions(transactions, replaceTags, applyFilter);
        datastore.storeTransactions(newTransactions, false);
    }
}