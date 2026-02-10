package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;

public interface TransactionParser {
    Set<Transaction> parse(final Reader reader) throws IOException;
}
