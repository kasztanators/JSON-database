package com.griddynamics.internship.client.args.parser.cliparser;

import com.griddynamics.internship.client.args.InMemoryCommandArgs;
import com.griddynamics.internship.client.args.parser.ArgsParser;

public interface CliArgsParser extends ArgsParser {
    String getCommandArgs(String[] args);
    String formatArgs(InMemoryCommandArgs args);
}

