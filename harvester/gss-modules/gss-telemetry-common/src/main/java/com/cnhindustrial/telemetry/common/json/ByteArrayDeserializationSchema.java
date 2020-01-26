package com.cnhindustrial.telemetry.common.json;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;

public class ByteArrayDeserializationSchema extends AbstractDeserializationSchema<byte[]> {

    private static final long serialVersionUID = -5208726850626044092L;

    @Override
    public byte[] deserialize(byte[] message) {
        return message;
    }
}
