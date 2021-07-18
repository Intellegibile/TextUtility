package me.intellegibile.textutility.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

public class OutputFile {
    private final Path path;

    public OutputFile(Path path) {
        this.path = path;
    }

    public void initializeOutput(String string) {
        try {
            Writer writer = new FileWriter(this.path.toFile());
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
