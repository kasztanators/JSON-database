package com.griddynamics.internship.fileio.filewriter;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileWriterImpl implements JsonFileWriter {
    private final Path path;

    public JsonFileWriterImpl(Path path) {
        this.path = path;
    }

    @Override
    public void write(Object object) {
        Gson gson = new Gson();
        String jsonData = gson.toJson(object);
        try {
            Files.write(path, jsonData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
