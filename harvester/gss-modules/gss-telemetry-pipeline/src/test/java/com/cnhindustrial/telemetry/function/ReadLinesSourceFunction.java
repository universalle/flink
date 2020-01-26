package com.cnhindustrial.telemetry.function;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadLinesSourceFunction implements SourceFunction<byte[]> {

    private static final long serialVersionUID = -2994223425802017905L;

    private final String filepath;

    public ReadLinesSourceFunction(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void run(SourceContext<byte[]> ctx) throws Exception {
        try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
            lines.map(line -> line.getBytes())
                    .forEach(bytes -> ctx.collect(bytes));
        }
    }

    @Override
    public void cancel() {
        // do nothing
    }
}
