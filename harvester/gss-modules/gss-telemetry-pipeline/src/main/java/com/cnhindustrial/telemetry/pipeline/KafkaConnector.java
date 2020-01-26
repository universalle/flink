package com.cnhindustrial.telemetry.pipeline;

import com.cnhindustrial.telemetry.common.exception.EnvironmentConfigurationException;
import com.cnhindustrial.telemetry.common.json.ByteArrayDeserializationSchema;

import com.cnhindustrial.telemetry.converter.TelemetrySerializationSchema;
import com.cnhindustrial.telemetry.common.model.TelemetryDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Factory configuring {@link FlinkKafkaConsumer} and {@link FlinkKafkaProducer} based on provided connection arguments.
 */
public class KafkaConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConnector.class);

    private final String topic;
    private final Properties properties;

    private KafkaConnector(String topic, Properties properties) {
        this.topic = topic;
        this.properties = properties;
    }

    private <T> FlinkKafkaConsumer<T> kafkaSource(DeserializationSchema<T> deserializationSchema) {
        LOGGER.info("Creating Kafka Consumer. Reading from last committed.");

        properties.setProperty("group.id", UUID.randomUUID().toString());
        properties.setProperty("receive.buffer.bytes", "32000000");
        properties.setProperty("max.poll.records", "1000000");
        properties.setProperty("request.timeout.ms", "60000");
        properties.setProperty("auto.offset.reset", "latest");

        return new FlinkKafkaConsumer<>(
                topic,
                deserializationSchema,
                properties);
    }

    public FlinkKafkaConsumer<byte[]> messageSource() {
        return kafkaSource(new ByteArrayDeserializationSchema());
    }

    private <T> FlinkKafkaProducer<T> kafkaSink(KafkaSerializationSchema<T> deserializationSchema) {
        LOGGER.info("Creating Kafka Consumer. Reading from last committed.");

        properties.setProperty("group.id", UUID.randomUUID().toString());
        properties.setProperty("batch.size", "33554432");
        properties.setProperty("buffer.memory", "33554432");
        properties.setProperty("linger.ms", "100");
        properties.setProperty("acks", "0");
        properties.setProperty("request.timeout.ms", "60000");
        properties.setProperty("auto.offset.reset", "latest");

        return new FlinkKafkaProducer<T>(
                topic,
                deserializationSchema,
                properties,
                FlinkKafkaProducer.Semantic.AT_LEAST_ONCE
        );
    }

    public FlinkKafkaProducer<TelemetryDto> deadLetterQueueSink() {
        return kafkaSink(new TelemetrySerializationSchema(topic));
    }

    public static class Builder {

        private static final Pattern EVENT_HUB_REGEX = Pattern.compile("Endpoint=sb:\\/\\/(.+)\\/;.+EntityPath=(.+)+;?");

        /**
         * Connect to Kafka.
         */
        public KafkaConnector buildKafkaConnector(String topic, String bootstrapServers) {
            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", bootstrapServers);
            return new KafkaConnector(topic, properties);
        }

        /**
         * Connect to Event Hubs using the Kafka protocol. https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-quickstart-kafka-enabled-event-hubs
         *
         * @param connectionString Event Hub connection string
         */
        public KafkaConnector buildEventHubConnector(String connectionString) {
            if (StringUtils.isBlank(connectionString)) {
                throw new EnvironmentConfigurationException("Connection string could not be blank. " +
                        "Please check EventHub connection string environment parameter.");
            }

            Matcher eventHubMatcher = EVENT_HUB_REGEX.matcher(connectionString);
            if (!eventHubMatcher.find()) {
                throw new EnvironmentConfigurationException("Unable to parse EventHub connection string. Regex: " + EVENT_HUB_REGEX);
            }
            String eventHubFqdn = eventHubMatcher.group(1);
            String topicName = eventHubMatcher.group(2);

            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", eventHubFqdn + ":9093");
            properties.setProperty("sasl.mechanism", "PLAIN");
            properties.setProperty("security.protocol", "SASL_SSL");
            properties.setProperty("sasl.jaas.config", getSaslJaasConfig(connectionString));

            return new KafkaConnector(topicName, properties);
        }

        private String getSaslJaasConfig(String eventHubConnectionString) {
            return "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$ConnectionString\" password="
                    + "\"" + eventHubConnectionString + "\";";
        }
    }
}
