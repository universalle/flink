package com.cnhindustrial.telemetry.converter;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;
import com.cnhindustrial.telemetry.model.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.locationtech.jts.geom.Point;

public class TelemetryConverter implements MapFunction<TelemetryDto, TelemetryFeatureWrapper>  {

    private PointConverter pointConverter;

    public TelemetryConverter() {
        pointConverter = new PointConverter();
    }

    @Override
    public TelemetryFeatureWrapper map(TelemetryDto telemetryDto) {
        TelemetryFeatureWrapper wrapper = new TelemetryFeatureWrapper();

        TelemetryStatusGeomesaFeature geomesaStatusFeature = TelemetryStatusGeomesaFeature.newInstance();
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.TIME, telemetryDto.getTime());
        Point point = pointConverter.convert(telemetryDto.getPosition().getLat(), telemetryDto.getPosition().getLon());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_POINT, point);
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.ASSET_ID, telemetryDto.getAssetId());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_PDOP, telemetryDto.getPosition().getPdop());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_CURRENT, telemetryDto.getPosition().isCurrent());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_SATCOUNT, telemetryDto.getPosition().getSatcount());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_FIXTYPE, telemetryDto.getPosition().getFixtype());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_ALT, telemetryDto.getPosition().getAlt());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_TIME, telemetryDto.getPosition().getTime());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_SPEED, telemetryDto.getPosition().getSpeed());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.POS_DIRECTION, telemetryDto.getPosition().getDirection());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.STATUS_DUTY, telemetryDto.getStatus().getDuty());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.STATUS_DEVICE, telemetryDto.getStatus().getDevice());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_RSSI, telemetryDto.getNetworkinfo().getRssi());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_MNC, telemetryDto.getNetworkinfo().getMnc());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_NETWORK_STATUS, telemetryDto.getNetworkinfo().getNetworkStatus());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_CONNECTION, telemetryDto.getNetworkinfo().getConnection());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_MCC, telemetryDto.getNetworkinfo().getMcc());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.NETWORKINFO_OPERATOR_NAME, telemetryDto.getNetworkinfo().getOperatorName());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.REPORTS, telemetryDto.getReports());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.EVENTS, telemetryDto.getEvents());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.DEVICE_ID, telemetryDto.getDeviceid());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.FROM, telemetryDto.getFrom());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.TO, telemetryDto.getTo());
        geomesaStatusFeature.setAttribute(TelemetryStatusFeaturePropertiesName.TECH_TYPE, telemetryDto.getTechType());
        wrapper.setStatusFeature(geomesaStatusFeature);

        telemetryDto.getTelemetryRecords().forEach(record -> {
            TelemetryValueGeomesaFeature geomesaValueFeature = TelemetryValueGeomesaFeature.newInstance();
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.TIME, record.getTime());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.POS_POINT, point);
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.ASSET_ID, record.getAssetId());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.FAMILY_CODE, record.getAssetId());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.ID, record.getId());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.DEVICE_ID, record.getDeviceId());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.CONFIDENTIAL, record.isConfidential());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.TRANSLATED_VALUE, record.getTranslatedValue());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.OFFSET, record.getOffset());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.SAMPLING_PERIOD, record.getSamplingPeriod());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.VALUE_TYPE, record.getValueType());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.REPORT_TYPE, record.getReportType());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.RESOLUTION, record.getResolution());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.PARAMETER_TYPE, record.getParameterType());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.TECH_TYPE, record.getTechType());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.IS_SIGNED, record.isIssigned());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.TO, record.getTo());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.FROM, record.getFrom());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.DECODED_VALUE, record.getDecodedValue());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.LAST, record.getLast());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.MIN, record.getMin());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.MAX, record.getMax());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.SUM, record.getSum());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.CNT, record.getCnt());
            geomesaValueFeature.setAttribute(TelemetryValueFeaturePropertiesName.LINE, record.getLine());
            wrapper.addValueFeature(geomesaValueFeature);
        });

        return wrapper;
    }
}
