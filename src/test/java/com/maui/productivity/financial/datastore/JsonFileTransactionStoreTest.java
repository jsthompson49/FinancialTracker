package com.maui.productivity.financial.datastore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class JsonFileTransactionStoreTest {

    private static final String ANY_PATH_TO_STORE = "AnyPathToStore";
    private static final File ANY_STORE_FILE = new File(ANY_PATH_TO_STORE);

    @Mock
    private ObjectMapper objectMapper;

    private JsonFileTransactionStore transactionStore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        transactionStore = new JsonFileTransactionStore(ANY_PATH_TO_STORE, objectMapper);
    }

    @Test
    public void testStoreTransactionReplaceSuccess() throws Exception {
        final List<TaggedTransaction> newTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);

        final ArgumentCaptor<List<TaggedTransaction>> storeTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(objectMapper).writeValue(eq(ANY_STORE_FILE), storeTransactions.capture());

        transactionStore.storeTransactions(newTransactions, false);

        assertThat(storeTransactions.getValue()).isEqualTo(newTransactions);
        verify(objectMapper, never()).readValue(any(File.class), any(TypeReference.class));
    }

    @Test
    public void testStoreTransactionAppendSuccess() throws Exception {
        final List<TaggedTransaction> existingTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS);
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(existingTransactions);

        final List<TaggedTransaction> newTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);

        final ArgumentCaptor<List<TaggedTransaction>> storeTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(objectMapper).writeValue(eq(ANY_STORE_FILE), storeTransactions.capture());

        transactionStore.storeTransactions(newTransactions, true);

        assertThat(storeTransactions.getValue()).isEqualTo(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATE_SORTED_COMBINED_TRANSACTIONS));
    }

    @Test
    public void testFetchTransactionsReturnList() throws Exception {
        final List<TaggedTransaction> expectedTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS);
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(expectedTransactions);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEqualTo(expectedTransactions);
    }

    @Test
    public void testFetchTransactionsReturnNull() throws Exception {
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(null);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEmpty();
    }

    @Test
    public void testFetchTransactionsReturnEmpty() throws Exception {
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(Collections.EMPTY_LIST);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEmpty();
    }
}
