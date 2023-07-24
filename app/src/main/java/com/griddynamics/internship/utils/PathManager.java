package com.griddynamics.internship.utils;

public class PathManager {
    private final static String BASE_PATH = "/Users/wczetyrbok/IdeaProjects/HardTask/wczetyrbok-hard-task/app/src/main/java/com/griddynamics/internship/";
    public static String getPath(String relativePath) {
        return BASE_PATH + relativePath;
    }
}
