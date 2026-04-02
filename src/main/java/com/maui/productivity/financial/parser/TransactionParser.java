package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface TransactionParser {
    boolean isValidFormat(final String data);
    List<Transaction> parse(final String data) throws IOException;
}
