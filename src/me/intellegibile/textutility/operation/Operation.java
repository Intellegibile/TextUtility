package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

public abstract class Operation {
    private final InputFile input;
    private final OutputFile output;

    public Operation(InputFile inputFile, OutputFile outputFile) {
        this.input = inputFile;
        this.output = outputFile;
    }

    public abstract void operate();
}
