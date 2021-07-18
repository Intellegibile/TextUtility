package me.intellegibile.textutility.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile {
    private final Path path;

    public InputFile(Path path) {
        this.path = path;
    }

    public String toString() {
        String content = null;
        try {
            content = Files.readString(this.path);
        } catch (IOException e) {
            e.getMessage();
        }

        return content;
    }

    public List<Character> getInputListCharacter() {
        List<Character> inputListCharacters = new ArrayList<>();
        for (int i = 0; i < this.toString().toCharArray().length; i++) {
            inputListCharacters.add(this.toString().toCharArray()[i]);
        }
        return inputListCharacters;
    }
}
