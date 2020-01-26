package com.cnhindustrial.telemetry.test;

import org.apache.flink.runtime.testutils.MiniClusterResourceConfiguration;
import org.apache.flink.test.util.MiniClusterWithClientResource;
import org.junit.Rule;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * https://ci.apache.org/projects/flink/flink-docs-stable/dev/stream/testing.html#junit-rule-miniclusterwithclientresource
 *
 * Adapter from JUnit 4 {@link Rule} interface to JUnit 5 {@link ExtendWith} interface.
 */
public class MiniClusterWithClientResourceExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private MiniClusterWithClientResource flinkCluster =
            new MiniClusterWithClientResource(
                    new MiniClusterResourceConfiguration.Builder()
                            .setNumberSlotsPerTaskManager(2)
                            .setNumberTaskManagers(1)
                            .build());

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        flinkCluster.before();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        flinkCluster.after();
    }
}
