package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

import java.util.ArrayList;
import java.util.List;

public class DeleteOperation extends Operation{
    private final String word;
    private final char[] chars;
    private List<Character> characterList = new ArrayList<Character>();

    public DeleteOperation(String word, InputFile inputFile, OutputFile outputFile) {
       super(inputFile, outputFile);
       this.word = word;
       this.chars = inputFile.toString().toCharArray();
       for (int i = 0; i < this.chars.length; i++) {
           this.characterList.add(this.chars[i]);
       }
    }

    public void deleteAllSpaces() {
        this.characterList = this.characterList.stream().filter(character -> !Character.isWhitespace(character.charValue())).toList();
    }

    @Override
    public void operate() {
        this.deleteAllSpaces();
        this.setOutputString(this.characterList);
    }
}
