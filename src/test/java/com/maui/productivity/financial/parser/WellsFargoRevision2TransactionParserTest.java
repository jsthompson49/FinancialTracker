package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WellsFargoRevision2TransactionParserTest {

    private final WellsFargoRevision2TransactionParser parser = new WellsFargoRevision2TransactionParser();

    @Test
    public void testIsValidFormatSuccess() {
        final boolean result = parser.isValidFormat(Artifacts.TEST_DATA_WF_REV2_CSV);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsValidFormatInvalid() {
        final boolean result = parser.isValidFormat(Artifacts.TEST_DATA_AE_CSV);

        assertThat(result).isFalse();
    }

    @Test
    public void testParseSuccess() throws Exception {
        final List<Transaction> transactions = parser.parse(Artifacts.TEST_DATA_WF_REV2_CSV);

        assertThat(transactions).isEqualTo(Artifacts.TEST_DATA_TRANSACTIONS);
    }

    @Test
    public void testParseNoInput() throws Exception {
        final List<Transaction> transactions = parser.parse("");

        assertThat(transactions).isEmpty();
    }
}
