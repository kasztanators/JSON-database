package com.griddynamics.internship.fileio;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonFileReader implements FileReader{
    private Map<String, String> parseToJson(String record) {
        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> result = new Gson().fromJson(record, mapType);
        return result != null
                ? result
                : new HashMap<>();
    }

    @Override
    public Map<String, String> read(Path filePath) {
        try {
            String fileData = Files.readString(filePath);
            return parseToJson(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
