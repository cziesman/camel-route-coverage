package com.redhat.consulting.camel.route.coverage.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "convert", description = "Converts the Camel Route Coverage output to HTML")
@Component
@Slf4j
public class ConverterCommand implements Callable<Integer> {

    @Autowired
    private CoverageResultsProcessor processor;

    @Option(names = {"-i", "--input"}, description = "The path to the Camel project directory", required = true)
    private String input;

    @Option(names = {"-h", "--help", "-?", "-help"}, usageHelp = true, description = "Display this help and exit")
    private boolean help;

    @Override
    public Integer call() {

        try {
            return processor.generateReport(input);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            System.out.printf("\n Report generation failed with error [%s]\n%n", ex.getMessage());

            return -1;
        }
    }
}
