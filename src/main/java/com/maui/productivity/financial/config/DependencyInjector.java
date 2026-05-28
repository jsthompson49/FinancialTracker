package com.maui.productivity.financial.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.budget.YearlyBudget;
import com.maui.productivity.financial.datastore.JsonFileTransactionStore;
import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.manager.ImportManager;
import com.maui.productivity.financial.manager.ReportManager;
import com.maui.productivity.financial.manager.TagManager;
import com.maui.productivity.financial.manager.tag.DesignatedTransactions;
import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.parser.AmericanExpressTransactionParser;
import com.maui.productivity.financial.parser.MultipleAutoDetectTransactionParser;
import com.maui.productivity.financial.parser.TransactionParser;
import com.maui.productivity.financial.parser.WellsFargoRevision2TransactionParser;
import com.maui.productivity.financial.parser.WellsFargoTransactionParser;
import com.maui.productivity.financial.report.HtmlReportGenerator;
import com.maui.productivity.financial.tracker.FinancialTracker;

import java.util.ArrayList;
import java.util.List;

public class DependencyInjector {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.findAndRegisterModules();
    }

    public FinancialTracker buildFinancialTracker(final ConfigurationManager configurationManager) {
        final TransactionStore datastore = new JsonFileTransactionStore(configurationManager.getPathToTransactionStore(), objectMapper);

        final List<TagRule> tagRules = new ArrayList<>();
        tagRules.addAll(Schema.TAG_RULES);
        tagRules.add(DesignatedTransactions.getDesignatedTransactions());
        tagRules.addAll(DesignatedTransactions.getCheckTagRules());
        final TagManager tagManager = new TagManager(tagRules);

        final TransactionParser transactionParser = new MultipleAutoDetectTransactionParser(List.of(
                new WellsFargoRevision2TransactionParser(),
                new AmericanExpressTransactionParser(),
                new WellsFargoTransactionParser()
        ));

        final ImportManager importManager = new ImportManager(transactionParser, tagManager);

        final YearlyBudget yearlyBudget = new YearlyBudget();

        final HtmlReportGenerator reportGenerator = new HtmlReportGenerator();
        final ReportManager reportManager = new ReportManager(tagManager, reportGenerator);

        return new FinancialTracker(datastore, tagManager, importManager, yearlyBudget, reportManager);
    }

}
