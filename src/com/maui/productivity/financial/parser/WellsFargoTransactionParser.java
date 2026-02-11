package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WellsFargoTransactionParser implements TransactionParser {

    private static final int DATE_COLUMN_INDEX = 0;
    private static final int AMOUNT_COLUMN_INDEX = 1;
    private static final int DESCRIPTION_COLUMN_INDEX = 4;

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public List<Transaction> parse(final Reader reader) throws IOException  {
        final CSVParser parser = CSVFormat.DEFAULT.parse(reader);

        final ArrayList<Transaction> transactions = new ArrayList<>();
        for (final CSVRecord record : parser) {
            final LocalDate date = LocalDate.parse(record.get(DATE_COLUMN_INDEX), DATE_FORMATTER);
            final double amount = Double.parseDouble(record.get(AMOUNT_COLUMN_INDEX));
            final String description = record.get(DESCRIPTION_COLUMN_INDEX);
            transactions.add(new Transaction(amount, date, description));
        }

        return transactions;
    }
}
