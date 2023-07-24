package com.griddynamics.internship.client.args.parser.fileparser;

import com.beust.jcommander.JCommander;
import com.griddynamics.internship.client.args.FileCommandArgs;

public class InFileCommandArgs {
    public static FileCommandArgs parseCommandArgs(String[] args) {
        FileCommandArgs inFileArgs = new FileCommandArgs();
        JCommander.newBuilder()
                .addObject(inFileArgs)
                .build()
                .parse(args);
        return inFileArgs;
    }
}
