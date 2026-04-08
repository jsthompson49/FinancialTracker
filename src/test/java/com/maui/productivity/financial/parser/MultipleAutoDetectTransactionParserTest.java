package com.maui.productivity.financial.parser;

import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MultipleAutoDetectTransactionParserTest {

    private static final String SOME_INVALID_FORMAT = "SomeInvalidFormat";

    @Mock private TransactionParser transactionParser1;
    @Mock private TransactionParser transactionParser2;

    private MultipleAutoDetectTransactionParser parser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        parser = new MultipleAutoDetectTransactionParser(List.of(transactionParser1, transactionParser2));
    }

    private static Stream<Arguments> provideIsValidFormatSuccess() {
        return Stream.of(
                Arguments.of(false, false, false),
                Arguments.of(true, false, true),
                Arguments.of(false, true, true),
                Arguments.of(true, true, true)
        );
    }
    @ParameterizedTest
    @MethodSource("provideIsValidFormatSuccess")
    public void testIsValidFormatSuccess(final boolean result1, final boolean result2, final boolean expectedResult) {
        when(transactionParser1.isValidFormat(Artifacts.TEST_DATA_CSV)).thenReturn(result1);
        when(transactionParser2.isValidFormat(Artifacts.TEST_DATA_CSV)).thenReturn(result2);

        final boolean actualResult = parser.isValidFormat(Artifacts.TEST_DATA_CSV);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testParseSuccess() throws Exception {
        when(transactionParser1.isValidFormat(Artifacts.TEST_DATA_CSV)).thenReturn(true);
        when(transactionParser1.parse(Artifacts.TEST_DATA_CSV)).thenReturn(Artifacts.TEST_DATA_TRANSACTIONS);

        final List<Transaction> transactions = parser.parse(Artifacts.TEST_DATA_CSV);

        assertThat(transactions).isEqualTo(Artifacts.TEST_DATA_TRANSACTIONS);
    }

    @Test
    public void testParseNoParser() throws Exception {
        when(transactionParser1.isValidFormat(SOME_INVALID_FORMAT)).thenReturn(false);
        when(transactionParser2.isValidFormat(SOME_INVALID_FORMAT)).thenReturn(false);

        final List<Transaction> transactions = parser.parse(SOME_INVALID_FORMAT);

        assertThat(transactions).isEmpty();

        verify(transactionParser1, never()).parse(SOME_INVALID_FORMAT);
        verify(transactionParser2, never()).parse(SOME_INVALID_FORMAT);
    }

    @Test
    public void testParseNoInput() throws Exception {
        when(transactionParser1.isValidFormat("")).thenReturn(true);
        when(transactionParser1.parse("")).thenReturn(Collections.EMPTY_LIST);

        final List<Transaction> transactions = parser.parse("");

        assertThat(transactions).isEmpty();
    }

}
