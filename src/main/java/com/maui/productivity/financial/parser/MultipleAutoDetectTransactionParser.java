package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RequiredArgsConstructor
public class MultipleAutoDetectTransactionParser implements TransactionParser {

    private final List<TransactionParser> transactionParsers;

    @Override
    public boolean isValidFormat(final String data) {
        return transactionParsers.stream().anyMatch(parser -> parser.isValidFormat(data));
    }

    @Override
    public List<Transaction> parse(final String data) throws IOException  {
        for(final TransactionParser parser : transactionParsers) {
            final boolean isValidFormat = parser.isValidFormat(data);
            if (isValidFormat) {
                return parser.parse(data);
            }
        }

        return List.of();
    }
}
