package com.maui.productivity.financial.tracker;

import com.maui.productivity.financial.config.DependencyInjector;
import com.maui.productivity.financial.config.ConfigurationManager;
import com.maui.productivity.financial.model.TaggedTransaction;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.function.Predicate;

@Log4j2
public class Main {

    private static final String CONFIG_FILE = "config-file";
    private static final String DRYRUN = "dryrun";
    private static final String IMPORT = "import";
    private static final String REPORT = "report";
    private static final String IMPORT_FILE_PATH = "importFilePath";

    public static void main(String[] args) {
        try {
            // Command Line options
            final Options options = new Options();
            options.addOption(CONFIG_FILE, true, "path to Java properties config file");
            options.addOption(DRYRUN, false, "Execute as dryrun only without saving, default: false");
            options.addOption(IMPORT, false, "Import transactions and save, default: false");
            options.addOption(REPORT, false, "Generate report of transactions, default: true");
            options.addOption(IMPORT_FILE_PATH, true, "Path to import transactions file");

            final CommandLine commandLine = new DefaultParser().parse(options, args);

            final DependencyInjector dependencyInjector = new DependencyInjector();
            final ConfigurationManager configurationManager = commandLine.hasOption(CONFIG_FILE)
                    ? new ConfigurationManager(commandLine.getOptionValue(CONFIG_FILE))
                    : new ConfigurationManager();
            final FinancialTracker financialTracker = dependencyInjector.buildFinancialTracker(configurationManager);

            //final Predicate<TaggedTransaction> reapplyFilter = TransactionFilters.getFilter(TransactionFilters.getDateOnFilter(LocalDate.of(2026, 2, 23)));
            final Predicate<TaggedTransaction> reapplyFilter = null;

            //final String pathToImportFile = "data/CreditCardAE-050226.csv";
            //final String pathToImportFile = "data/Checking-050126.csv";
            //final String pathToImportFile = "data/CreditCardSecondary-050126.csv";
            //final String pathToImportFile = "data/CreditCard-050226.csv";

            financialTracker.execute(
                    commandLine.hasOption(DRYRUN),
                    commandLine.hasOption(IMPORT),
                    commandLine.hasOption(REPORT),
                    reapplyFilter,
                    commandLine.getParsedOptionValue(IMPORT_FILE_PATH));
        } catch (final Exception e) {
            log.error("Error in program", e);
        }
    }
}