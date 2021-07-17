package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class PrintOperation extends Operation{

    public PrintOperation(InputFile inputFile, OutputFile outputFile) {
        super(inputFile, outputFile);
    }

    public void operate() {

    }
}
