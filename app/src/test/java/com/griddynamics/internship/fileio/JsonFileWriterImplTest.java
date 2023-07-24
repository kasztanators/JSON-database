package com.griddynamics.internship.fileio;

import com.griddynamics.internship.fileio.filewriter.JsonFileWriterImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JsonFileWriterImplTest {

    @Test
    public void testWriteToFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("data.json");
        JsonFileWriterImpl fileWriter = new JsonFileWriterImpl(filePath);

        Map<String, String> dataToWrite = new HashMap<>();
        dataToWrite.put("key1", "value1");
        dataToWrite.put("key2", "value2");

        fileWriter.write(dataToWrite);

        String writtenContent = Files.readString(filePath);

        String expectedJson = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        assertEquals(expectedJson, writtenContent);
    }
}