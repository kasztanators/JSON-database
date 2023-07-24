package com.griddynamics.internship.server.userinput;

public class InputReader {
    public static String [] readArguments(String inputLine) {
        return inputLine.split("\\s+");
    }

    public static int readNumber(String input) throws NumberFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input. Please enter a valid number.");
        }
    }
}
