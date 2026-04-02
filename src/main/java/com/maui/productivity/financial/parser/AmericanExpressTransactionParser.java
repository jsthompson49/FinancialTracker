package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AmericanExpressTransactionParser implements TransactionParser {

    private static final int DATE_COLUMN_INDEX = 0;
    private static final int DESCRIPTION_COLUMN_INDEX = 1;
    private static final int AMOUNT_COLUMN_INDEX = 4;
    private static final int MINIMUM_COLUMN_COUNT = 5;

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public boolean isValidFormat(final String data) {
        try {
            final CSVParser parser = CSVFormat.DEFAULT.parse(new StringReader(data));
            final CSVRecord firstRow = parser.getRecords().getFirst();

            if (firstRow.size() < MINIMUM_COLUMN_COUNT) {
                log.info("Invalid format minimum column count: actual={} expected={}",
                        firstRow.size(), MINIMUM_COLUMN_COUNT);
            }

            if (firstRow.get(DATE_COLUMN_INDEX).equals("Date")
                    && firstRow.get(DESCRIPTION_COLUMN_INDEX).equals("Description")
                    && firstRow.get(AMOUNT_COLUMN_INDEX).equals("Amount")) {
                return true;
            }
            log.info("Invalid format headers do not match");
        } catch (final IOException exception) {
            log.info("Invalid format", exception);
        }

        return false;
    }

    @Override
    public List<Transaction> parse(final String data) throws IOException  {
        final CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get();
        final CSVParser parser = format.parse(new StringReader(data));

        final ArrayList<Transaction> transactions = new ArrayList<>();
        for (final CSVRecord record : parser) {
            final LocalDate date = LocalDate.parse(record.get(DATE_COLUMN_INDEX), DATE_FORMATTER);
            final double amount = Double.parseDouble(record.get(AMOUNT_COLUMN_INDEX)) * -1.0;
            final String description = record.get(DESCRIPTION_COLUMN_INDEX);
            transactions.add(new Transaction(amount, date, description));
        }

        return transactions;
    }
}
