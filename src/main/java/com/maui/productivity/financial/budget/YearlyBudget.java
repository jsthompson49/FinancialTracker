package com.maui.productivity.financial.budget;

import com.maui.productivity.financial.manager.tag.Schema;

import java.time.Year;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class YearlyBudget {

    private static final HashMap<String, Double> mapBuilder = new HashMap<>();
    static {
        mapBuilder.put(Schema.CAR, 4_000.0);
        mapBuilder.put(Schema.CASH, 2_000.0);
        mapBuilder.put(Schema.DONATION, 2_000.0);
        mapBuilder.put(Schema.GIFT, 10_000.0);
        mapBuilder.put(Schema.GROCERIES, 18_000.0);
        mapBuilder.put(Schema.HEALTH, 30_000.0);
        mapBuilder.put(Schema.HOBBIES, 3_000.0);
        mapBuilder.put(Schema.HOME, 15_000.0);
        mapBuilder.put(Schema.INCOME_TAX, 24_000.0);
        mapBuilder.put(Schema.INSURANCE, 5_000.0);
        mapBuilder.put(Schema.MEDIA, 2_000.0);
        mapBuilder.put(Schema.PETS, 8_000.0);
        mapBuilder.put(Schema.PROPERTY_TAX, 8_000.0);
        mapBuilder.put(Schema.RESTAURANTS, 18_000.0);
        mapBuilder.put(Schema.SERVICES, 2_000.0);
        mapBuilder.put(Schema.SHOPPING, 15_000.0);
        mapBuilder.put(Schema.SHOWS, 2_000.0);
        mapBuilder.put(Schema.SPORTING_EVENT, 8_000.0);
        mapBuilder.put(Schema.TRAVEL, 40_000.0);
        mapBuilder.put(Schema.UTILITIES, 12_000.0);
    }
    private static final Set<String> expensesBuilder = new HashSet<>();
    static {
        expensesBuilder.addAll(mapBuilder.keySet());

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
            Schema.GIFT, Set.of(Schema.GIFT),
            Schema.HEALTH, Set.of(Schema.HEALTH),
            Schema.INCOME_TAX, Set.of(Schema.INCOME_TAX),
            Schema.TRAVEL, Set.of(Schema.TRAVEL),
            "Expenses", Set.copyOf(expensesBuilder)
    );

    private static Map<String, Double> CATEGORY_AMOUNTS = Map.copyOf(mapBuilder);

    public Map<String, Set<String>> getCategories(final Year year) {
        return MAJOR_CATEGORIES;
    }

    public Map<String, Double> getAmounts(final Year year) {
        return CATEGORY_AMOUNTS;
    }

    public Set<String> getAllCatgories() {
        return MAJOR_CATEGORIES.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

}
