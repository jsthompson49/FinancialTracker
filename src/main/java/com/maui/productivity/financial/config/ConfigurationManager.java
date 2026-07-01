package com.maui.productivity.financial.config;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigurationManager {
    private static final String DEFAULT_CONFIG_PROPERTIES_RESOURCE_FILE_NAME = "config.properties";

    private static final String DATASTORE_PATH_TO_TRANSACTIONS_JSON = "datastore.pathToTransactionsJson";
    private static final String DATASTORE_PATH_TO_META_DATA = "datastore.pathToMetaData";

    final Properties properties = new Properties();

    public ConfigurationManager() {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_PROPERTIES_RESOURCE_FILE_NAME)) {
            properties.load(input);

            log.info("List resource properties: {}", properties.entrySet());
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ConfigurationManager(final String pathToConfigFile) {
        try (InputStream input = new FileInputStream(pathToConfigFile)) {
            properties.load(input);

            log.info("List properties from {}: {}", pathToConfigFile, properties.entrySet());
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getPathToTransactionStore() {
        return properties.getProperty(DATASTORE_PATH_TO_TRANSACTIONS_JSON);
    }

    public String getDatastorePathToMetaData() {
        return properties.getProperty(DATASTORE_PATH_TO_META_DATA);
    }
}
