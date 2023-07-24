package com.griddynamics.internship.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.griddynamics.internship.exceptions.NoSuchKeyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonUtilsTest {

    @Test
    public void testFindElement() {
        JsonObject records = new JsonObject();
        records.addProperty("key1", "value1");
        records.addProperty("key2", "value2");

        JsonArray keys = new JsonArray();
        keys.add("key1");

        JsonElement result = JsonUtils.findElement(keys, records);

        assertEquals("value1", result.getAsString());
    }

    @Test
    public void testFindElementWithNoSuchKeyException() {
        JsonObject records = new JsonObject();
        records.addProperty("key1", "value1");

        JsonArray keys = new JsonArray();
        keys.add("nonExistentKey");

        assertThrows(NoSuchKeyException.class, () -> JsonUtils.findElement(keys, records));
    }

    @Test
    public void testFindElementWithNonPrimitiveKey() {
        JsonObject records = new JsonObject();
        records.addProperty("key1", "value1");

        JsonArray keys = new JsonArray();
        keys.add(new JsonObject()); // Non-primitive key

        assertThrows(NoSuchKeyException.class, () -> JsonUtils.findElement(keys, records));
    }

    @Test
    public void testCreateElement() {
        JsonObject records = new JsonObject();
        records.addProperty("key1", "value1");

        JsonArray keys = new JsonArray();
        keys.add("key1");

        JsonElement result = JsonUtils.createElement(keys, records);

        JsonObject expected = new JsonObject();
        expected.add("key1", new JsonObject());

        assertEquals(expected, result);
    }

    @Test
    public void testCreateElementWithExistingKey() {
        JsonObject records = new JsonObject();
        records.addProperty("key1", "value1");

        JsonArray keys = new JsonArray();
        keys.add("key1");

        JsonElement result = JsonUtils.createElement(keys, records);

        JsonObject expected = new JsonObject();
        expected.addProperty("key1", "value1");

        assertEquals(expected, result);
    }
}
