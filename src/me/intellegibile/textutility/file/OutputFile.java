package me.intellegibile.textutility.file;

import java.io.FileOutputStream;

public class OutputFile {
    private final FileOutputStream fileOutputStream;

    public OutputFile(FileOutputStream outputStream) {
        this.fileOutputStream = outputStream;
    }
}
