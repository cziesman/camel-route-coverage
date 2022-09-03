package com.redhat.consulting.camel.route.coverage.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.redhat.consulting.camel.route.coverage.model.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class XmlToCamelRouteCoverageConverter implements Converter<String, TestResult> {

    @Override
    public TestResult convert(String source) {

        Map<String, Object> map;

        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            map = xmlMapper.readValue(source, Map.class);

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            return objectMapper.readValue(json, TestResult.class);
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage(), ex);

            return null;
        }
    }
}
