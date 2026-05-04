package com.maui.productivity.financial.test;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Artifacts {
    private static final double AMOUNT_1 = -412.06;
    private static final double AMOUNT_2 = -9017.34;

    private static final double AMOUNT_A = -53.12;
    private static final double AMOUNT_B = 78.89;

    private static final LocalDate DATE_1 = LocalDate.of(2026, 1, 29);
    private static final LocalDate DATE_2 = LocalDate.of(2026, 2, 28);

    private static final LocalDate DATE_A = LocalDate.of(2026, 1, 10);
    private static final LocalDate DATE_B = LocalDate.of(2026, 2, 8);

    private static final String DESC_1 = "Desc #1";
    private static final String DESC_2 = "Desc #2";

    private static final String DESC_A = "Desc #A";
    private static final String DESC_B = "Desc #B";

    public static final String TAG_NAME_A = "NameA";
    public static final String TAG_NAME_B = "NameB";
    public static final String TAG_NAME_C = "NameC";

    public static final String TAG_VALUE_A_1 = "AValue1";
    public static final String TAG_VALUE_B_1 = "BValue1";

    public static final Tag TAG_A_1 = new Tag(TAG_NAME_A, TAG_VALUE_A_1);
    public static final Tag TAG_A_2 = new Tag(TAG_NAME_A, "AValue2");
    public static final Tag TAG_B_1 = new Tag(TAG_NAME_B, TAG_VALUE_B_1);

    public static final String TEST_DATA_CSV = "\"01/29/2026\",\"-412.06\",\"*\",\"\",\"Desc #1\"\r\n"
            + "\"02/28/2026\",\"-9017.34\",\"*\",\"\",\"Desc #2\"";

    public static final String TEST_DATA_AE_CSV = "Date,Description,Card Member,Account #,Amount\r\n"
            + "\"01/29/2026\",\"Desc #1\",\"\",\"\",\"412.06\"\r\n"
            + "\"02/28/2026\",\"Desc #2\",\"\",\"\",\"9017.34\"";

    public static final String TEST_DATA_WF_REV2_CSV = "\"DATE\",\"DESCRIPTION\",\"AMOUNT\",\"CHECK #\",\"STATUS\"\r\n"
            + "\"01/29/2026\",\"Desc #1\",\"-412.06\",\"\",\"\"\r\n"
            + "\"02/28/2026\",\"Desc #2\",\"-9017.34\",\"\",\"\"";

    public static final List<Transaction> TEST_DATA_TRANSACTIONS = List.of(
            new Transaction(AMOUNT_1, DATE_1, DESC_1),
            new Transaction(AMOUNT_2, DATE_2, DESC_2)
    );

    public static final List<Transaction> TEST_IMPORT_TRANSACTIONS = List.of(
            new Transaction(AMOUNT_A, DATE_A, DESC_A),
            new Transaction(AMOUNT_B, DATE_B, DESC_B)
    );

    public static final List<Transaction> TEST_DATE_SORTED_COMBINED_TRANSACTIONS = List.of(
            TEST_IMPORT_TRANSACTIONS.get(0),
            TEST_DATA_TRANSACTIONS.get(0),
            TEST_IMPORT_TRANSACTIONS.get(1),
            TEST_DATA_TRANSACTIONS.get(1)
    );

    public static final List<TaggedTransaction> buildTaggedTransactions(final List<Transaction> transactions) {
        return buildTaggedTransactions(transactions, Collections.EMPTY_SET);
    }

    public static final List<TaggedTransaction> buildTaggedTransactions(final List<Transaction> transactions, final Set<Tag> tags) {
        return transactions.stream()
                .map(transaction -> new TaggedTransaction(transaction, tags))
                .toList();
    }

    public static final List<TaggedTransaction> buildTaggedTransactions(final List<Transaction> transactions, final List<Set<Tag>> tagsList) {
        final ArrayList<TaggedTransaction> list = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            list.add(new TaggedTransaction(transactions.get(i), tagsList.get(i)));
        }

        return list;
    }

    public static final List<TaggedTransaction> mergeTaggedTransactions(final List<TaggedTransaction> transactions1,
                                                                        final List<TaggedTransaction> transactions2) {
        final ArrayList<TaggedTransaction> merged = new ArrayList<>();
        merged.addAll(transactions1);
        merged.addAll(transactions2);

        return merged;
    }
}
