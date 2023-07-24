package com.griddynamics.internship.fileio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonFileReaderTest {
    private final JsonFileReader fileReader = new JsonFileReader();
    @Test
    public void testReadValidJsonFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("valid.json");
        String jsonContent = "{\"key\":\"value\"}";
        Files.write(filePath, jsonContent.getBytes());

        Map<String, String> result = fileReader.read(filePath);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(Map.of("key", "value"), result);
    }

    @Test
    public void testReadEmptyJsonFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("empty.json");
        String jsonContent = "";
        Files.write(filePath, jsonContent.getBytes());

        Map<String, String> result = fileReader.read(filePath);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testReadNonExistentFile() {
        Path nonExistentFilePath = Path.of("path/to/nonExistentFile.json");

        Assertions.assertThrows(RuntimeException.class, () -> fileReader.read(nonExistentFilePath));
    }
}