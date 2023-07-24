package com.griddynamics.internship.fileio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.griddynamics.internship.fileio.filewriter.ComplexJsonFileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexJsonFileWriterTest {

    @Test
    public void testWriteToFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("data.json");
        ComplexJsonFileWriter fileWriter = new ComplexJsonFileWriter(filePath);

        JsonObject dataToWrite = new JsonObject();
        dataToWrite.addProperty("name", "John");
        dataToWrite.addProperty("age", 30);

        fileWriter.write(dataToWrite);

        String writtenContent = Files.readString(filePath);

        String expectedJson =
                """
                        {
                          "name": "John",
                          "age": 30
                        }""";

        assertEquals(expectedJson, writtenContent);
    }
    @Test
    public void testWriteEmptyJsonObject(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("data.json");
        ComplexJsonFileWriter fileWriter = new ComplexJsonFileWriter(filePath);

        JsonObject dataToWrite = new JsonObject();

        fileWriter.write(dataToWrite);

        String writtenContent = Files.readString(filePath);

        assertEquals("{}", writtenContent);
    }
    @Test
    public void testWriteJsonObjectWithArrays(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("data.json");
        ComplexJsonFileWriter fileWriter = new ComplexJsonFileWriter(filePath);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("value1");
        jsonArray.add("value2");
        jsonArray.add("value3");

        JsonObject dataToWrite = new JsonObject();
        dataToWrite.addProperty("name", "Test");
        dataToWrite.addProperty("id", 3);
        dataToWrite.add("values", jsonArray);

        fileWriter.write(dataToWrite);

        String writtenContent = Files.readString(filePath);

        Gson gson = new Gson();
        String expectedJson = gson.toJson(dataToWrite);

        String writtenContentNoSpaces = writtenContent.replaceAll("[\\r\\n\\s]", "");
        //removed spaces and new lines for testing reason
        assertEquals(expectedJson, writtenContentNoSpaces);
    }
}
