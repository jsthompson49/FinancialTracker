package com.maui.productivity.financial.tracker;

import com.maui.productivity.financial.config.DependencyInjector;
import com.maui.productivity.financial.config.ConfigurationManager;
import com.maui.productivity.financial.model.TaggedTransaction;
import lombok.extern.log4j.Log4j2;

import java.util.function.Predicate;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Hello and welcome!");

        final DependencyInjector dependencyInjector = new DependencyInjector();
        final ConfigurationManager configurationManager = new ConfigurationManager();
        final FinancialTracker financialTracker = dependencyInjector.buildFinancialTracker(configurationManager);

        final boolean dryrun = false;
        final boolean importTransactions = false;
        final boolean report = true;
        //final Predicate<TaggedTransaction> reapplyFilter = TransactionFilters.getFilter(TransactionFilters.getDateOnFilter(LocalDate.of(2026, 2, 23)));
        final Predicate<TaggedTransaction> reapplyFilter = null;

        final String pathToImportFile = "data/CreditCardAE-050226.csv";
        //final String pathToImportFile = "data/Checking-050126.csv";
        //final String pathToImportFile = "data/CreditCardSecondary-050126.csv";
        //final String pathToImportFile = "data/CreditCard-050226.csv";

        financialTracker.execute(dryrun, importTransactions, report, reapplyFilter, pathToImportFile);
    }
}