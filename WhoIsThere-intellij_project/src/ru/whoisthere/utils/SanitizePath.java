package ru.whoisthere.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SanitizePath {
    public static String sanitizePathTraversal(String filename) {
        Path p = Paths.get(filename);
        return p.getFileName().toString();
    }
}
