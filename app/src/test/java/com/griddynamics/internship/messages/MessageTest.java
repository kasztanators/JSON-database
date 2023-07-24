package com.griddynamics.internship.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {

    @Test
    public void testMessageFromCli() {
        String[] args = {"-t", "get", "-k", "key"};


        Message message = new MessageFromCli();
        String result = message.get(args);

        assertEquals("{\"type\":\"get\",\"key\":\"key\"}", result);
    }
    @Test
    public void testMessageFromCliWithTypeSet() {
        String[] args = {"-t", "set", "-k", "key", "-v", "value"};

        Message message = new MessageFromCli();
        String result = message.get(args);

        assertEquals("{\"type\":\"set\",\"key\":\"key\",\"value\":\"value\"}", result);
    }
    @Test
    public void testMessageFromCliWithMissingKey() {
        String[] args = {"-t", "get"};

        Message message = new MessageFromCli();
        String result = message.get(args);

        assertEquals("{\"type\":\"get\"}", result);
    }

    @Test
    public void testMessageFromCliWithMissingType() {
        String[] args = {"-k", "key"};

        Message message = new MessageFromCli();
        String result = message.get(args);

        assertEquals("{\"key\":\"key\"}", result);
    }
    @Test
    public void testMessageFromCliWithEmptyArgs() {
        String[] args = {};

        Message message = new MessageFromCli();
        String result = message.get(args);

        assertEquals("{}", result);
    }

    @Test
    public void testMessageFromFile(){
        String fileContent = "{\"type\":\"get\",\"key\":[\"person\",\"name\"]}";

        String[] args = {"-in", "testGet.json"};

        Message message = new MessageFromFile();
        String result = message.get(args);

        assertEquals(fileContent, result);
    }

}
