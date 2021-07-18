package me.intellegibile.textutility.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile {
    private final Path path;

    public InputFile(Path path) {
        this.path = path;
    }

    public String toString() {
        String content = null;
        try {
            content = Files.readString(this.path);
        } catch (IOException e) {
            e.getMessage();
        }

        return content;
    }
}
