package com.griddynamics.internship.inputreader;

import com.griddynamics.internship.server.userinput.InputReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputReaderTest {
    @Test
    public void readArguments_singleSpaceInput() {
        String inputLine = "set 1 is alex";
        String[] result = InputReader.readArguments(inputLine);
        String[] expected = {"set", "1", "is", "alex"};

        assertArrayEquals(expected, result);
    }
    @Test
    public void readArguments_multipleSpacesTest(){
        String inputLine = "my    name   is alex";
        String[] result = InputReader.readArguments(inputLine);
        String[] expected = {"my", "name", "is", "alex"};

        assertArrayEquals(expected, result);
    }
    @Test
    public void readArguments_specialCharsInputTest(){
        String inputLine = "#!$  *@#!  ()) +}:";
        String[] result = InputReader.readArguments(inputLine);
        String[] expected = {"#!$", "*@#!", "())", "+}:"};

        assertArrayEquals(expected, result);
    }
    @Test
    public void readNumber_testValidInput() {
        String input = "123";
        try {
            int result = InputReader.readNumber(input);
            assertEquals(123, result);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test()
    public void readNumber_testInvalidInput() {
        String input = "abc";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> InputReader.readNumber(input));
        String expectedMessage = "Invalid input. Please enter a valid number.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
