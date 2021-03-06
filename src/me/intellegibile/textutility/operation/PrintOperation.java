package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.Pattern.Pattern;
import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

public class PrintOperation extends Operation{

    public PrintOperation(InputFile inputFile, OutputFile outputFile) {
        super(inputFile, outputFile);
    }

    public void operate(Pattern pattern) {
        System.out.println(this.input.toString());
    }
}
