package com.griddynamics.internship.client.args.parser.cliparser;

import com.beust.jcommander.JCommander;
import com.griddynamics.internship.client.args.InMemoryCommandArgs;

public class JcommanderParser {
    public static InMemoryCommandArgs parseCommandArgs(String[] args) {
        InMemoryCommandArgs commandArgs = new InMemoryCommandArgs();
        JCommander.newBuilder()
                .addObject(commandArgs)
                .build()
                .parse(args);
        return commandArgs;
    }
}
