package com.griddynamics.internship.fileio;

import java.nio.file.Path;
import java.util.Map;

public interface FileReader {
    Map<String, String> read(Path filePath);
}
