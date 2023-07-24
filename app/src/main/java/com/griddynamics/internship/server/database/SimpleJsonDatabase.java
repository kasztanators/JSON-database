package com.griddynamics.internship.server.database;

import com.google.gson.JsonElement;
import com.griddynamics.internship.fileio.FileReader;
import com.griddynamics.internship.fileio.JsonFileReader;
import com.griddynamics.internship.fileio.filewriter.JsonFileWriter;
import com.griddynamics.internship.fileio.filewriter.JsonFileWriterImpl;
import com.griddynamics.internship.utils.PathManager;

import java.nio.file.Path;
import java.util.Map;

public class SimpleJsonDatabase extends InMemoryDatabase implements Database{
    private final JsonFileWriter fileWriter;
    private static final Path DB_PATH = Path.of(PathManager.getPath("server/data/simple.json"));
    public SimpleJsonDatabase() {
        super(loadRecords());
        loadRecords();
        this.fileWriter = new JsonFileWriterImpl(DB_PATH);
    }

    @Override
    public void setRecord(JsonElement key, JsonElement data) {
        super.setRecord(key, data);
        fileWriter.write(getRecords());
    }

    @Override
    public void deleteRecord(JsonElement key) {
        super.deleteRecord(key);
        fileWriter.write(getRecords());
    }

    private static Map<String, String> loadRecords(){
        FileReader reader = new JsonFileReader();
        return reader.read(DB_PATH);
    }
}
