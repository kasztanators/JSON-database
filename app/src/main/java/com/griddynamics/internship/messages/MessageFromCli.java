package com.griddynamics.internship.messages;

import com.griddynamics.internship.client.args.parser.cliparser.CliArgsParser;
import com.griddynamics.internship.client.args.parser.cliparser.JsonParser;

public class MessageFromCli implements Message {
    @Override
    public String get(String[] args) {
        CliArgsParser parser  = new JsonParser();
        return  parser.getCommandArgs(args);
    }
}
