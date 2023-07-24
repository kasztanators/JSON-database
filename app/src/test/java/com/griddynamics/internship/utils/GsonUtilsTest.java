package com.griddynamics.internship.utils;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class GsonUtilsTest {

    @Test
    public void testPrettyPrint() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "John");
        jsonObject.addProperty("age", 30);

        String prettyPrintedJson = GsonUtils.prettyPrint(jsonObject);

        String expectedOutput = "{\n  \"name\": \"John\",\n  \"age\": 30\n}";
        assertEquals(expectedOutput, prettyPrintedJson);
    }
}
