package com.griddynamics.internship.client.args.parser.cliparser;

import com.beust.jcommander.ParameterException;
import com.griddynamics.internship.client.args.InMemoryCommandArgs;

public class StringParser implements CliArgsParser {

    @Override
    public String getCommandArgs(String[] args) {
        try {
            InMemoryCommandArgs commandArgs = JcommanderParser.parseCommandArgs(args);
            return formatArgs(commandArgs);
        } catch (ParameterException e) {
            throw new RuntimeException("Failed to parse the command-line arguments.", e);
        }
    }

    @Override
    public String formatArgs(InMemoryCommandArgs args) {
        StringBuilder builder = new StringBuilder();
        builder.append(args.getType());

        if (args.getKey() != null) {
            builder.append(" ").append(args.getKey());
        }
        if (args.getValue() != null) {
            builder.append(" ").append(args.getValue());
        }
        return builder.toString();
    }
}
