package com.griddynamics.internship.args;

import com.griddynamics.internship.client.args.parser.ArgsParser;
import com.griddynamics.internship.client.args.parser.fileparser.FileArgsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileArgsParserTest {
    private final ArgsParser parser;

    public FileArgsParserTest() {
        this.parser = new FileArgsParser();
    }

    @Test
    public void testParsingArgsWithValidInput() {
        String[] args = "-in testGet.json".split(" ");

        String result = parser.getCommandArgs(args);

        String expected = "testGet.json";
        Assertions.assertEquals(result, expected);
    }
    @Test
    public void testParsingArgsWithInValidInput() {
        String[] args = "-in testWrong.json json".split(" ");

        RuntimeException e = Assertions.assertThrows(RuntimeException.class,
                () -> parser.getCommandArgs(args));
        Assertions.assertEquals("Failed to parse the command-line arguments.", e.getMessage());
    }
}
