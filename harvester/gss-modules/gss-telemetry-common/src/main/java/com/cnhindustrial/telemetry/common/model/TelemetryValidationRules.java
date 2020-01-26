package com.cnhindustrial.telemetry.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.util.*;

import static com.cnhindustrial.telemetry.common.model.Constants.*;

public enum TelemetryValidationRules implements ValidationRule<TelemetryDto> {

    INVALID_VEHICLE_ID {
        @Override
        public boolean validate(TelemetryDto value) {
            List<String> list = Arrays.asList(Constants.ASSET_IDS);
            if (!list.contains(value.getAssetId())) {
                LOGGER.info("Invalid asset ID");
            }
            return list.contains(value.getAssetId());
        }
    },
    TIMESTAMP_AFTER {
        @Override
        public boolean validate(TelemetryDto value) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("EET")));
            Date now = calendar.getTime();
            if (value.getTime().after(now)) {
                LOGGER.info("Timestamp after current time");
            }
            return value.getTime().before(now);
        }
    },
    TIMESTAMP_BEFORE {
        @Override
        public boolean validate(TelemetryDto value) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("UTC")));
            calendar.set(2000, Calendar.JANUARY, 1);
            Date minDate = calendar.getTime();
            if (value.getTime().before(minDate)) {
                LOGGER.info("Timestamp less than 2000 year");
            }
            return value.getTime().after(minDate);
        }
    },
    INVALID_TIME_FORMAT {
        @Override
        public boolean validate(TelemetryDto value) {
            return true;
        }
    },
    LONGITUDE_LESS_THAN_MIN {
        @Override
        public boolean validate(TelemetryDto value) {
            if (value.getPosition().getLon() < MIN_LONGITUDE) {
                LOGGER.info("Longitude less than min");
            }
            return value.getPosition().getLon() >= MIN_LONGITUDE;
        }
    },
    LONGITUDE_BIGGER_THAN_MAX {
        @Override
        public boolean validate(TelemetryDto value) {
            if (value.getPosition().getLon() > MAX_LONGITUDE) {
                LOGGER.info("Longitude bigger than max");
            }
            return value.getPosition().getLon() <= MAX_LONGITUDE;
        }
    },
    LATITUDE_LESS_THAN_MIN {
        @Override
        public boolean validate(TelemetryDto value) {
            if (value.getPosition().getLat() < MIN_LATITUDE) {
                LOGGER.info("Latitude less than min");
            }
            return value.getPosition().getLat() >= MIN_LATITUDE;
        }
    },
    LATITUDE_BIGGER_THAN_MAX {
        @Override
        public boolean validate(TelemetryDto value) {
            if (value.getPosition().getLat() > MAX_LATITUDE) {
                LOGGER.info("Latitude bigger than max");
            }
            return value.getPosition().getLat() <= MAX_LATITUDE;
        }
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetryValidationRules.class.getName());
}
