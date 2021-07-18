package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;
import me.intellegibile.textutility.util.StringReader;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation {
    private final InputFile input;
    private final OutputFile output;
    private final StringReader stringReader;
    private final String string;
    private String outputString;
    protected List<Character> characterOutput = new ArrayList<>();

    public Operation(InputFile inputFile, OutputFile outputFile) {
        this.input = inputFile;
        this.output = outputFile;
        this.string = inputFile.toString();
        this.stringReader = new StringReader(this.string);
    }

    public StringReader getStringReader() {
        return this.stringReader;
    }

    public String getString() {
        return this.getString();
    }

    public void setOutputString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.characterOutput.stream().forEach(character -> stringBuilder.append(character));
        this.outputString = stringBuilder.toString();
    }

    public abstract void operate();
}
