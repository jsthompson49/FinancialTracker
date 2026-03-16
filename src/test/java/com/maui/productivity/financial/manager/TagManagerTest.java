package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.model.Tag;
import com.maui.productivity.financial.model.TagRule;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagManagerTest {

    private static final String UNDEFINED_TAG_VALUE = "<Undefined>";

    @Mock private TagRule rule1;
    @Mock private TagRule rule2;
    @Mock private TagRule rule3;

    private TagManager tagManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        tagManager = new TagManager(List.of(rule1, rule2, rule3));
    }

    @Test
    public void testTagTaggedTransactionsFilter() {
        final Set<Tag> originalTagSet2 = Set.of(Artifacts.TAG_B_1);
        final List<TaggedTransaction> transactionsToTag = Artifacts.buildTaggedTransactions(
                Artifacts.TEST_DATA_TRANSACTIONS,
                List.of(Set.of(Artifacts.TAG_A_1), originalTagSet2));
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSet1 = Set.of(Artifacts.TAG_A_2);

        when(rule1.apply(transaction1)).thenReturn(expectedTagSet1);

        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag, true,
                TransactionFilters.getFilter(TransactionFilters.getDateOnFilter(LocalDate.of(2026, 1, 29))));

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS, List.of(expectedTagSet1, originalTagSet2));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);

        verify(rule2, never()).apply(transaction2);
    }

    @Test
    public void testTagTaggedTransactionsReplace() {
        final List<TaggedTransaction> transactionsToTag = Artifacts.buildTaggedTransactions(
                Artifacts.TEST_DATA_TRANSACTIONS,
                List.of(Set.of(Artifacts.TAG_A_1), Set.of(Artifacts.TAG_B_1)));
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSet1 = Set.of(Artifacts.TAG_A_2);
        final Set<Tag> expectedTagSet2 = Set.of();

        when(rule1.apply(transaction1)).thenReturn(expectedTagSet1);
        when(rule2.apply(transaction2)).thenReturn(expectedTagSet2);

        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag, true);

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS, List.of(expectedTagSet1, expectedTagSet2));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);
    }

    @Test
    public void testTagTaggedTransactionsMerge() {
        final List<TaggedTransaction> transactionsToTag = Artifacts.buildTaggedTransactions(
                Artifacts.TEST_DATA_TRANSACTIONS,
                List.of(Set.of(Artifacts.TAG_A_1), Set.of(Artifacts.TAG_B_1)));
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSet1 = Set.of(Artifacts.TAG_A_2);
        final Set<Tag> expectedTagSet2 = Set.of(Artifacts.TAG_A_1, Artifacts.TAG_B_1);

        when(rule1.apply(transaction1)).thenReturn(expectedTagSet1);
        when(rule2.apply(transaction2)).thenReturn(Set.of(Artifacts.TAG_A_1));

        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag, false);

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS, List.of(expectedTagSet1, expectedTagSet2));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);
    }

    @Test
    public void testTagTransactions() {
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSet1 = Set.of(Artifacts.TAG_A_1);
        final Set<Tag> expectedTagSet2 = Set.of(Artifacts.TAG_B_1);

        when(rule1.apply(transaction1)).thenReturn(expectedTagSet1);
        when(rule2.apply(transaction2)).thenReturn(expectedTagSet2);

        final List<Transaction> transactionsToTag = List.of(transaction1, transaction2);
        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag);

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(transactionsToTag, List.of(expectedTagSet1, expectedTagSet2));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);
    }

    @Test
    public void testTagTransactionsRuleOverride() {
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSet1 = Set.of(Artifacts.TAG_A_1);
        final Set<Tag> expectedTagSetOverride = Set.of(Artifacts.TAG_A_2);

        when(rule1.apply(transaction1)).thenReturn(expectedTagSet1);
        when(rule3.apply(transaction1)).thenReturn(expectedTagSetOverride);

        final List<Transaction> transactionsToTag = List.of(transaction1, transaction2);
        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag);

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(transactionsToTag, List.of(expectedTagSetOverride, Collections.EMPTY_SET));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);
    }

    @Test
    public void testTagTransactionsMultipleRulesAggregate() {
        final Transaction transaction1 = Artifacts.TEST_DATA_TRANSACTIONS.get(0);
        final Transaction transaction2 = Artifacts.TEST_DATA_TRANSACTIONS.get(1);

        final Set<Tag> expectedTagSetAggregate = Set.of(Artifacts.TAG_A_1, Artifacts.TAG_B_1);

        when(rule1.apply(transaction1)).thenReturn(Set.of(Artifacts.TAG_A_1));
        when(rule3.apply(transaction1)).thenReturn(Set.of(Artifacts.TAG_B_1));

        final List<Transaction> transactionsToTag = List.of(transaction1, transaction2);
        final List<TaggedTransaction> taggedTransactions = tagManager.tagTransactions(transactionsToTag);

        final List<TaggedTransaction> expectedTaggedTransactions =
                Artifacts.buildTaggedTransactions(transactionsToTag, List.of(expectedTagSetAggregate, Collections.EMPTY_SET));

        assertThat(taggedTransactions).isEqualTo(expectedTaggedTransactions);
    }

    @Test
    public void testHasTagNullTags() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(null);

        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_A)).isFalse();
        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_B)).isFalse();
    }

    @Test
    public void testHasTagNoTags() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of());

        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_A)).isFalse();
        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_B)).isFalse();
    }

    @Test
    public void testHasTagSingleTag() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of(Artifacts.TAG_A_1));

        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_A)).isTrue();
        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_B)).isFalse();
    }

    @Test
    public void testHasTagMultipleTags() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of(Artifacts.TAG_A_1, Artifacts.TAG_B_1));

        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_A)).isTrue();
        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_B)).isTrue();
        assertThat(tagManager.hasTag(taggedTransaction, Artifacts.TAG_NAME_C)).isFalse();
    }

    @Test
    public void testGetTagValueNoTags() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of());

        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_A)).isEqualTo(UNDEFINED_TAG_VALUE);
    }

    @Test
    public void testGetTagValueSingleTag() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of(Artifacts.TAG_A_1));

        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_A)).isEqualTo(Artifacts.TAG_VALUE_A_1);
        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_B)).isEqualTo(UNDEFINED_TAG_VALUE);
    }

    @Test
    public void testGetTagValueMultipleTags() {
        final TaggedTransaction taggedTransaction = mock(TaggedTransaction.class);
        when(taggedTransaction.getTags()).thenReturn(Set.of(Artifacts.TAG_A_1, Artifacts.TAG_B_1));

        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_A)).isEqualTo(Artifacts.TAG_VALUE_A_1);
        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_B)).isEqualTo(Artifacts.TAG_VALUE_B_1);
        assertThat(tagManager.getTagValue(taggedTransaction, Artifacts.TAG_NAME_C)).isEqualTo(UNDEFINED_TAG_VALUE);
    }

}
