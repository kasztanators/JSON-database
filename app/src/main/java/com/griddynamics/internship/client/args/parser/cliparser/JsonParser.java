package com.griddynamics.internship.client.args.parser.cliparser;


import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.griddynamics.internship.client.args.InMemoryCommandArgs;

public class JsonParser implements CliArgsParser {
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
        Gson gson = new Gson();
        return gson.toJson(args);
    }
}
