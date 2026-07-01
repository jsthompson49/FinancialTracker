package com.maui.productivity.financial.budget;

import com.maui.productivity.financial.manager.tag.Schema;
import com.maui.productivity.financial.parser.BudgetParser;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class YearlyBudget {

    private final BudgetParser budgetParser;
    private final String pathToDatastore;

    private static final String YEARLY_BUDGET_FILE_FORMAT = "%s/yearlyBudget-%s.csv";

    private static final Set<String> categoryBuilder = new HashSet<>();
    static {
        categoryBuilder.add(Schema.CAR);
        categoryBuilder.add(Schema.CASH);
        categoryBuilder.add(Schema.DONATION);
        categoryBuilder.add(Schema.GIFT);
        categoryBuilder.add(Schema.GROCERIES);
        categoryBuilder.add(Schema.HEALTH);
        categoryBuilder.add(Schema.HOBBIES);
        categoryBuilder.add(Schema.HOME);
        categoryBuilder.add(Schema.INCOME_TAX);
        categoryBuilder.add(Schema.INSURANCE);
        categoryBuilder.add(Schema.MEDIA);
        categoryBuilder.add(Schema.PETS);
        categoryBuilder.add(Schema.PROPERTY_TAX);
        categoryBuilder.add(Schema.RESTAURANTS);
        categoryBuilder.add(Schema.SERVICES);
        categoryBuilder.add(Schema.SHOPPING);
        categoryBuilder.add(Schema.SHOWS);
        categoryBuilder.add(Schema.SPORTING_EVENT);
        categoryBuilder.add(Schema.TRAVEL);
        categoryBuilder.add(Schema.UTILITIES);
    }
    private static final Set<String> expensesBuilder = new HashSet<>();
    static {
        expensesBuilder.addAll(categoryBuilder);

        // Gift
        expensesBuilder.remove(Schema.GIFT);

        // Health
        expensesBuilder.remove(Schema.HEALTH);

        // Taxes
        expensesBuilder.remove(Schema.INCOME_TAX);

        // Travel
        expensesBuilder.remove(Schema.TRAVEL);
    }

    private static Map<String, Set<String>> MAJOR_CATEGORIES = Map.of(
            Schema.GIFT, Set.of(Schema.GIFT, Schema.TUITION),
            Schema.HEALTH, Set.of(Schema.HEALTH),
            Schema.INCOME_TAX, Set.of(Schema.INCOME_TAX),
            Schema.TRAVEL, Set.of(Schema.TRAVEL),
            Schema.REAL_ESTATE, Set.of(Schema.REAL_ESTATE_COUNTY_ROAD_F),
            "Expenses", Set.copyOf(expensesBuilder)
    );

    public Map<String, Set<String>> getCategories(final Year year) {
        return MAJOR_CATEGORIES;
    }

    public Set<String> getAllCategories() {
        return MAJOR_CATEGORIES.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Map<String, Double> getAmounts(final Year year) {
        try {
            final String content = new String(Files.readAllBytes(Paths.get(getPathToBudgetFile(year))), StandardCharsets.UTF_8);
            return budgetParser.parse(content);
        } catch (final IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private String getPathToBudgetFile(final Year year) {
        return String.format(YEARLY_BUDGET_FILE_FORMAT, pathToDatastore, year.getValue());
    }
}
