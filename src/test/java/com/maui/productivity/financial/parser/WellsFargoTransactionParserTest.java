package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WellsFargoTransactionParserTest {

    private final WellsFargoTransactionParser parser = new WellsFargoTransactionParser();

    private static final String TEST_DATA = "\"01/29/2026\",\"-412.06\",\"*\",\"\",\"Desc #1\"\r\n"
            + "\"01/28/2026\",\"-9017.34\",\"*\",\"\",\"Desc #2\"";

    @Test
    public void testParseSuccess() throws Exception {
        final List<Transaction> transactions = parser.parse(new StringReader(TEST_DATA));

        assertThat(transactions.size()).isEqualTo(2);
    }

    @Test
    public void testParseNoInput() throws Exception {
        final List<Transaction> transactions = parser.parse(new StringReader(""));

        assertThat(transactions).isEmpty();
    }
}
