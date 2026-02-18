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
        mapBuilder.put(Schema.CAR, 5_000.0);
        mapBuilder.put(Schema.CASH, 3_000.0);
        mapBuilder.put(Schema.DONATION, 2_000.0);
        mapBuilder.put(Schema.ENTERTAINMENT_EVENT, 8_000.0);
        mapBuilder.put(Schema.GROCERIES, 15_000.0);
        mapBuilder.put(Schema.HEALTH, 30_000.0);
        mapBuilder.put(Schema.HOBBIES, 1_000.0);
        mapBuilder.put(Schema.HOME, 15_000.0);
        mapBuilder.put(Schema.INSURANCE, 4_500.0);
        mapBuilder.put(Schema.MEDIA, 800.0);
        mapBuilder.put(Schema.MOVIES, 500.0);
        mapBuilder.put(Schema.PETS, 8_000.0);
        mapBuilder.put(Schema.RESTAURANTS, 10_000.0);
        mapBuilder.put(Schema.SERVICES, 2_000.0);
        mapBuilder.put(Schema.SHOPPING, 20_000.0);
        mapBuilder.put(Schema.STREAMING_SERVICE, 500.0);
        mapBuilder.put(Schema.TAXES, 20_000.0);
        mapBuilder.put(Schema.TRAVEL, 50_000.0);
        mapBuilder.put(Schema.UTILITIES, 12_000.0);
    }
    private static final Set<String> expensesBuilder = new HashSet<>();
    static {
        expensesBuilder.addAll(mapBuilder.keySet());

        // Health
        expensesBuilder.remove(Schema.HEALTH);

        // Travel
        expensesBuilder.remove(Schema.TRAVEL);
    }

    private static Map<String, Set<String>> MAJOR_CATEGORIES = Map.of(
            Schema.HEALTH, Set.of(Schema.HEALTH),
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
