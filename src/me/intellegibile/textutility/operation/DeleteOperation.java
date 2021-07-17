package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteOperation extends Operation{
    private final String word;

    public DeleteOperation(String word, InputFile inputFile, OutputFile outputFile) {
       super(inputFile, outputFile);
       this.word = word;
    }

    @Override
    public void operate() {

    }
}
