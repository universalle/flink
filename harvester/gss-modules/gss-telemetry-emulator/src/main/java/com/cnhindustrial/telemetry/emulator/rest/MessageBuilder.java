package com.cnhindustrial.telemetry.emulator.rest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_29;

public abstract class MessageBuilder {
    private static final Logger logger = LoggerFactory.getLogger(MessageBuilder.class);

    public abstract String getTemplate();

    public abstract String getTemplateName();


    String build(Map<String, Object> inputParams) {

        String templateName = getTemplateName();
        String messageTemplate = getTemplate();

        String jsonMetadata = "";
        try {
            Template template = new Template(templateName, new StringReader(messageTemplate), new Configuration(VERSION_2_3_29));

            StringWriter stringWriter = new StringWriter();
            template.process(inputParams, stringWriter);
            jsonMetadata = stringWriter.toString();
        } catch (IOException | TemplateException e) {
            logger.error("Failed to generate " + templateName + "message");
        }

        return jsonMetadata;
    }
}
