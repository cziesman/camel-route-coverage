package com.redhat.consulting.camel.route.coverage;

import com.redhat.consulting.camel.route.coverage.process.ConverterCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class CamelRouteCoverageApplication implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;

    private final ConverterCommand converterCommand;

    private int exitCode;

    public static void main(String[] args) {

        System.exit(SpringApplication.exit(SpringApplication.run(CamelRouteCoverageApplication.class, args)));
    }

    public CamelRouteCoverageApplication(IFactory factory, ConverterCommand converterCommand) {

        this.factory = factory;
        this.converterCommand = converterCommand;
    }

    @Override
    public void run(String... args) {

        exitCode = new CommandLine(converterCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {

        return exitCode;
    }
}
