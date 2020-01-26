package com.cnhindustrial.telemetry.factory;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

import com.cnhindustrial.telemetry.common.exception.EnvironmentConfigurationException;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoMesaDataStoreFactory implements Function<Map<String, String>, DataStore> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoMesaDataStoreFactory.class);

    @Override
    public DataStore apply(Map<String, String> configuration) {
        LOGGER.debug("Loading data store using configuration: {}.", configuration);
        return loadDataStore(configuration);
    }

    protected void validateDataStoreConfiguration(Map<String, String> configuration) {
        // do nothing, let subclasses override
    }

    /**
     * Use geotools service loading to get a datastore instance.
     */
    protected DataStore loadDataStore(Map<String, String> configuration) {
        try {
            DataStore result = DataStoreFinder.getDataStore(configuration);
            if (result == null) {
                throw new EnvironmentConfigurationException("Could not create data store using provided parameters.");
            }
            return result;
        } catch (IOException e) {
            throw new EnvironmentConfigurationException("Error creating data store.", e);
        }
    }
}
