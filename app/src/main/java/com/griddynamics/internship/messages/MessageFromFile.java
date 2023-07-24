package com.griddynamics.internship.messages;

import com.griddynamics.internship.client.args.parser.ArgsParser;
import com.griddynamics.internship.client.args.parser.fileparser.FileArgsParser;
import com.griddynamics.internship.utils.PathManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MessageFromFile implements Message{
    @Override
    public String get(String[] args) {
        ArgsParser parser  = new FileArgsParser();
        String commandArgs = parser.getCommandArgs(args);
        String REPOSITORY_ROOT_PATH = "client/data/";

        String filePath = PathManager.getPath(REPOSITORY_ROOT_PATH + commandArgs);
        Path path = Paths.get(filePath);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
