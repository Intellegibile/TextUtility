package me.intellegibile.textutility.operation;

import me.intellegibile.textutility.Pattern.DeletePattern;
import me.intellegibile.textutility.Pattern.Pattern;
import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;

import java.util.ArrayList;
import java.util.List;

public class DeleteOperation extends Operation{
    private final List<String> words;

    public DeleteOperation(List<String> words, InputFile inputFile, OutputFile outputFile) {
       super(inputFile, outputFile);
       this.words = words;
    }

    public void deleteAllSpaces() {
        this.characterOutput = this.characterOutput.stream().filter(character -> !Character.isWhitespace(character.charValue())).toList();
    }

    public void deleteWords() {
        List<String> wordsToDelete = this.getStringReader().getWordsBetweenSpaces();
       wordsToDelete = wordsToDelete.stream().filter(s -> !this.words.contains(s)).toList();
       this.setOutputString(wordsToDelete);
    }

    public void deleteEverything() {
        List<String> nothing = new ArrayList<>();
        this.setOutputString(nothing);
    }

    @Override
    public void operate(Pattern pattern) {
        if (pattern == DeletePattern.DELETE_ALL_SPACES) {
            this.deleteAllSpaces();
        } else if (pattern == DeletePattern.DELETE_SPECIFIC_WORD) {
            this.deleteWords();
        } else if (pattern == DeletePattern.DELETE_EVERYTHING) {
            this.deleteEverything();
        }
        this.sendOutput();
    }
}
