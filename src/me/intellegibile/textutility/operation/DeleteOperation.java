package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

import java.util.ArrayList;
import java.util.List;

public class DeleteOperation extends Operation{
    private ArrayList<Character> first = new ArrayList<Character>();

    public DeleteOperation(String first_, InputFile inputFile, OutputFile outputFile) {
        super(inputFile, outputFile);
        for (char c : first_.toCharArray()) {
            first.add(c);
        }
    }

    public void deleteAllSpaces() {
        this.getStringReader().spacePositions();
        List<Integer> spacePosition = this.getStringReader().getSpacePositions();
        System.out.println(spacePosition);
    }

    @Override
    public void operate() {

    }
}
