package com.cnhindustrial.telemetry.pipeline;

import com.cnhindustrial.telemetry.test.MachineDataCollectSink;
import com.cnhindustrial.telemetry.test.MiniClusterWithClientResourceExtension;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(MiniClusterWithClientResourceExtension.class)
class ControllerDataPipelineTest {

    private StreamExecutionEnvironment see;
    private MachineDataCollectSink machineDataCollectSink;
    private FunctionFactory functionFactory;

    @BeforeEach
    void setUp() {
        see = StreamExecutionEnvironment.getExecutionEnvironment();

        // configure your test environment
        see.setParallelism(2);

        Map<String, String> map = new HashMap<>();
        map.put("blob.storage.controller.data.path", "src/test/resources/com/cnhindustrial/telemetry/data/controller");
        map.put("environment.test", "true");
        ParameterTool parameters = ParameterTool
                .fromMap(map);
        functionFactory = new FunctionFactory(parameters);

        machineDataCollectSink = new MachineDataCollectSink();
    }

    @AfterEach
    void tearDown() {
        machineDataCollectSink.clear();
    }

    @Test
    void execute() throws Exception {
        ControllerDataPipeline controllerDataPipeline = new ControllerDataPipeline(
                functionFactory.getControllerDataSource(see),
                new PrintSinkFunction<>()
        );

        controllerDataPipeline.build(see);
        controllerDataPipeline.execute(see);

//        assertThat(machineDataCollectSink.getValues(), containsInAnyOrder(
//                "ControllerDto{id='c36841da-db98-893c-d4da-77241a7746f4', description='18/02/26-11:04:24 - Operation name: Cane Harvest'}",
//                "ControllerDto{id='36ca6711-c8a7-17e8-662c-4f63de4cba53', description='19/02/06-16:28:49 - Operation name: Other'}",
//                "ControllerDto{id='eb558274-367f-6695-db52-b40ab970ed65', description='17/10/18-11:54:21 - Operation name: Grain Harv'}"
//        ));
    }
}