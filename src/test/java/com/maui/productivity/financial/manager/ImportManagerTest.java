package com.maui.productivity.financial.manager;

import com.maui.productivity.financial.datastore.TransactionStore;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.parser.TransactionParser;
import com.maui.productivity.financial.test.Artifacts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ImportManagerTest {

    private static final String ANY_PATH_TO_IMPORT_FILE = "src/test/resources/AnyImport.csv";

    @Mock private TransactionParser transactionParser;
    @Mock private TagManager tagManager;
    @Mock private TransactionStore transactionStore;

    private ImportManager importManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        importManager = new ImportManager(transactionParser, tagManager);
    }

    @Test
    public void testImportTransactionsDryrunKeepDuplicatesSuccess() throws Exception {
        when(transactionParser.parse(any(FileReader.class))).thenReturn(Artifacts.TEST_IMPORT_TRANSACTIONS);

        when(transactionStore.fetchTransactions()).thenReturn(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATE_SORTED_COMBINED_TRANSACTIONS));

        final List<TaggedTransaction> expectedTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);
        when(tagManager.tagTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS)).thenReturn(expectedTransactions);

        final List<TaggedTransaction> dryrunTransactions =
                importManager.importTransactionsDryrun(ANY_PATH_TO_IMPORT_FILE, transactionStore, false);

        assertThat(dryrunTransactions).isEqualTo(expectedTransactions);
        verify(transactionStore, never()).storeTransactions(any(), anyBoolean());
    }

    @Test
    public void testImportTransactionsApplyTagsSuccess() throws Exception {
        when(transactionParser.parse(any(FileReader.class))).thenReturn(Artifacts.TEST_IMPORT_TRANSACTIONS);

        when(transactionStore.fetchTransactions()).thenReturn(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS));

        final List<TaggedTransaction> expectedTransactions =
                Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS,
                        List.of(Set.of(Artifacts.TAG_A_1), Set.of(Artifacts.TAG_A_2, Artifacts.TAG_B_1)));
        when(tagManager.tagTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS)).thenReturn(expectedTransactions);

        final ArgumentCaptor<List<TaggedTransaction>> storedTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(transactionStore).storeTransactions(storedTransactions.capture(), eq(true));

        importManager.importTransactions(ANY_PATH_TO_IMPORT_FILE, transactionStore, true);

        assertThat(storedTransactions.getValue()).isEqualTo(expectedTransactions);
    }

    @Test
    public void testImportTransactionsKeepDuplicatesSuccess() throws Exception {
        when(transactionParser.parse(any(FileReader.class))).thenReturn(Artifacts.TEST_IMPORT_TRANSACTIONS);

        when(transactionStore.fetchTransactions()).thenReturn(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATE_SORTED_COMBINED_TRANSACTIONS));

        final List<TaggedTransaction> expectedTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);
        when(tagManager.tagTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS)).thenReturn(expectedTransactions);

        final ArgumentCaptor<List<TaggedTransaction>> storedTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(transactionStore).storeTransactions(storedTransactions.capture(), eq(true));

        importManager.importTransactions(ANY_PATH_TO_IMPORT_FILE, transactionStore, false);

        assertThat(storedTransactions.getValue()).isEqualTo(expectedTransactions);
    }

    @Test
    public void testImportTransactionsRemoveDuplicatesSuccess() throws Exception {
        when(transactionParser.parse(any(FileReader.class))).thenReturn(Artifacts.TEST_IMPORT_TRANSACTIONS);

        when(transactionStore.fetchTransactions()).thenReturn(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATE_SORTED_COMBINED_TRANSACTIONS));

        final List<TaggedTransaction> expectedTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);
        when(tagManager.tagTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS)).thenReturn(expectedTransactions);

        final ArgumentCaptor<List<TaggedTransaction>> storedTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(transactionStore).storeTransactions(storedTransactions.capture(), eq(true));

        importManager.importTransactions(ANY_PATH_TO_IMPORT_FILE, transactionStore, true);

        assertThat(storedTransactions.getValue()).isEqualTo(Collections.EMPTY_LIST);
    }
        @Test
    public void testImportTransactionsRemoveDuplicatesNoDupicatesSuccess() throws Exception {
        when(transactionParser.parse(any(FileReader.class))).thenReturn(Artifacts.TEST_IMPORT_TRANSACTIONS);

        when(transactionStore.fetchTransactions()).thenReturn(Artifacts.buildTaggedTransactions(Artifacts.TEST_DATA_TRANSACTIONS));

        final List<TaggedTransaction> expectedTransactions = Artifacts.buildTaggedTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS);
        when(tagManager.tagTransactions(Artifacts.TEST_IMPORT_TRANSACTIONS)).thenReturn(expectedTransactions);

        final ArgumentCaptor<List<TaggedTransaction>> storedTransactions = ArgumentCaptor.forClass(List.class);
        doNothing().when(transactionStore).storeTransactions(storedTransactions.capture(), eq(true));

        importManager.importTransactions(ANY_PATH_TO_IMPORT_FILE, transactionStore, true);

        assertThat(storedTransactions.getValue()).isEqualTo(expectedTransactions);
    }
}
