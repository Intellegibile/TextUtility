package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;
import me.intellegibile.textutility.util.StringReader;

public abstract class Operation {
    private final InputFile inputFile;
    private final OutputFile outputFile;
    private final StringReader stringReader;

    protected Operation(InputFile inputFile, OutputFile outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.stringReader = new StringReader(inputFile.toCharArray().toString());
    }

    public abstract void operate();


}
