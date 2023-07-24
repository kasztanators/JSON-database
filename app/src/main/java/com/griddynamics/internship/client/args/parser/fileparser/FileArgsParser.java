package com.griddynamics.internship.client.args.parser.fileparser;

import com.beust.jcommander.ParameterException;
import com.griddynamics.internship.client.args.FileCommandArgs;
import com.griddynamics.internship.client.args.parser.ArgsParser;

public class FileArgsParser implements ArgsParser {
    @Override
    public String getCommandArgs(String[] args) {
        try {
            FileCommandArgs commandArgs = InFileCommandArgs.parseCommandArgs(args);
            return commandArgs.getFilename();
        } catch (ParameterException e) {
            throw new RuntimeException("Failed to parse the command-line arguments.", e);
        }
    }
}
