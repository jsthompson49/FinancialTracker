package com.maui.productivity.financial.datastore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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
    public void testFetchTransactionReturnList() throws Exception {
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class)))
                .thenReturn(Artifacts.TEST_DATA_TAGGED_TRANSACTIONS);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEqualTo(Artifacts.TEST_DATA_TAGGED_TRANSACTIONS);
    }

    @Test
    public void testFetchTransactionReturnNull() throws Exception {
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(null);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEmpty();
    }

    @Test
    public void testFetchTransactionReturnEmpty() throws Exception {
        when(objectMapper.readValue(eq(ANY_STORE_FILE), any(TypeReference.class))).thenReturn(Collections.EMPTY_LIST);

        final List<TaggedTransaction> transactions = transactionStore.fetchTransactions();

        assertThat(transactions).isEmpty();
    }
}
