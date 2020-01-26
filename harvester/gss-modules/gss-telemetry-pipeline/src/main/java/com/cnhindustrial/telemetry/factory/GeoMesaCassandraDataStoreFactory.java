package com.cnhindustrial.telemetry.factory;

import com.cnhindustrial.telemetry.common.exception.EnvironmentConfigurationException;
import org.geotools.data.DataAccessFactory.Param;
import org.geotools.data.DataStore;
import org.locationtech.geomesa.cassandra.data.CassandraDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GeoMesaCassandraDataStoreFactory extends GeoMesaDataStoreFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoMesaCassandraDataStoreFactory.class);

    private CassandraDataStoreFactory factory;

    public GeoMesaCassandraDataStoreFactory() {
        factory = new CassandraDataStoreFactory();
    }

    @Override
    protected void validateDataStoreConfiguration(Map<String, String> configuration) {
        Param[] parameters = factory.getParametersInfo();
        for (Param parameter : parameters) {
            if (parameter.isRequired() && configuration.get(parameter.getName()) == null) {
                LOGGER.warn("No configuration value is provided for required parameter '{}': {}",
                        parameter.getName(), parameter.getDescription());
                throw new EnvironmentConfigurationException("No configuration value is provided for required parameter "
                        + parameter.getName());
            }
        }
    }

    @Override
    protected DataStore loadDataStore(Map configuration) {
        return factory.createDataStore(configuration);
    }
}
