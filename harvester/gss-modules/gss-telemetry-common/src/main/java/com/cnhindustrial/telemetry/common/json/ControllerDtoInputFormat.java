package com.cnhindustrial.telemetry.common.json;

import org.apache.flink.api.common.io.DelimitedInputFormat;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;

import java.io.IOException;

// TODO remove
public class ControllerDtoInputFormat<T> extends DelimitedInputFormat<T> implements ResultTypeQueryable<T> {

    private static final long serialVersionUID = -5573383126778920786L;

    private TypeInformation<T> resultType;

    private DeserializationSchema<T> serializer;
    private boolean end;

    public ControllerDtoInputFormat(TypeInformation<T> resultType) {
        this.resultType = resultType;
        this.serializer = new BaseDeserializationSchema<>(resultType.getTypeClass());
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return resultType;
    }

    @Override
    public T readRecord(T reuse, byte[] bytes, int offset, int numBytes) throws IOException {

        String string = new String(bytes);

        end = true;

        return serializer.deserialize(bytes);

    }

    @Override
    public boolean reachedEnd() {
        return end;
    }
}
