package com.griddynamics.internship.fileio.filewriter;

import com.griddynamics.internship.utils.GsonUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ComplexJsonFileWriter  implements JsonFileWriter {
    private final Path path;

    public ComplexJsonFileWriter(Path path) {
        this.path = path;
    }
    @Override
    public void write(Object toWrite) {
        try {
            Files.write(path, GsonUtils.prettyPrint(toWrite).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
