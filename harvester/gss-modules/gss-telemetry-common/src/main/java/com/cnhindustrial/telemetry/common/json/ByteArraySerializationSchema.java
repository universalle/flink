package com.cnhindustrial.telemetry.common.json;

import org.apache.flink.api.common.serialization.SerializationSchema;

public class ByteArraySerializationSchema implements SerializationSchema<byte[]> {

    private static final long serialVersionUID = -5464037599034685937L;

    @Override
    public byte[] serialize(byte[] bytes) {
        return bytes;
    }

}