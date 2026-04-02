package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WellsFargoTransactionParserTest {

    private final WellsFargoTransactionParser parser = new WellsFargoTransactionParser();

    @Test
    public void testParseSuccess() throws Exception {
        final List<Transaction> transactions = parser.parse(Artifacts.TEST_DATA_CSV);

        assertThat(transactions).isEqualTo(Artifacts.TEST_DATA_TRANSACTIONS);
    }

    @Test
    public void testParseNoInput() throws Exception {
        final List<Transaction> transactions = parser.parse("");

        assertThat(transactions).isEmpty();
    }
}
