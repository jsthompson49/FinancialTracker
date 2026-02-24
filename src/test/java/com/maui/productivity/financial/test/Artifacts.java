package com.maui.productivity.financial.test;

import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Artifacts {
    private static final double AMOUNT_1 = -412.06;
    private static final double AMOUNT_2 = -9017.34;

    private static final LocalDate DATE_1 = LocalDate.of(2026, 1, 29);
    private static final LocalDate DATE_2 = LocalDate.of(2026, 1, 28);

    private static final String DESC_1 = "Desc #1";
    private static final String DESC_2 = "Desc #2";


    public static final String TEST_DATA_CSV = "\"01/29/2026\",\"-412.06\",\"*\",\"\",\"Desc #1\"\r\n"
            + "\"01/28/2026\",\"-9017.34\",\"*\",\"\",\"Desc #2\"";

    public static final List<Transaction> TEST_DATA_TRANSACTIONS = List.of(
            new Transaction(AMOUNT_1, DATE_1, DESC_1),
            new Transaction(AMOUNT_2, DATE_2, DESC_2)
    );

    public static final List<TaggedTransaction> TEST_DATA_TAGGED_TRANSACTIONS = TEST_DATA_TRANSACTIONS.stream()
            .map(transaction -> new TaggedTransaction(transaction, Collections.EMPTY_SET))
            .toList();
}
