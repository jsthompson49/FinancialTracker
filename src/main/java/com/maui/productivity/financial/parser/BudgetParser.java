package com.maui.productivity.financial.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class BudgetParser {

    private static final int CATEGORY_COLUMN_INDEX = 0;
    private static final int AMOUNT_COLUMN_INDEX = 1;

    public Map<String, Double> parse(final String data) throws IOException  {
        final CSVParser parser = CSVFormat.DEFAULT.parse(new StringReader(data));

        final HashMap<String, Double> categoryAmountMap = new HashMap<>();
        for (final CSVRecord record : parser) {
            final String category = record.get(CATEGORY_COLUMN_INDEX);
            final double amount = Double.parseDouble(record.get(AMOUNT_COLUMN_INDEX));
            categoryAmountMap.put(category, amount);
        }

        return categoryAmountMap;
    }
}
