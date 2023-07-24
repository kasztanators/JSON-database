package com.griddynamics.internship.args;

import com.griddynamics.internship.client.args.parser.ArgsParser;
import com.griddynamics.internship.client.args.parser.cliparser.StringParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CliArgsParserTest {
    private final ArgsParser parser;

    public CliArgsParserTest() {
        this.parser = new StringParser();
    }


    @Test
    public void testParsingArgsWithOneWordValue() {
        String[] args = "-t set -k 100 -v important".split(" ");

        String result = parser.getCommandArgs(args);

        String expected = "set 100 important";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testParsingArgsWithMultipleWordValue_shouldThrowException() {
        String[] args = "-t set -k 100 -v test value 12345 !!@$$%".split(" ");


        RuntimeException e = Assertions.assertThrows(RuntimeException.class,
                () -> parser.getCommandArgs(args));
        Assertions.assertEquals("Failed to parse the command-line arguments.", e.getMessage());
    }
    @Test void invalidTypeCharacterTest(){
        String[] args = "-g set -k 100 -m test".split(" ");

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> parser.getCommandArgs(args));

        String expectedMessage = "Failed to parse the command-line arguments.";
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
