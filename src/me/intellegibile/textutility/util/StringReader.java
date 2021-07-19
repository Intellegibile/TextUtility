package me.intellegibile.textutility.util;

import java.util.*;
import java.util.stream.Collectors;

public class StringReader {
    private final String string;
    private int cursor;
    private List<String> words = new ArrayList<String>();
    private List<String> command = new ArrayList<String>();
    private ArrayList<ArrayList<String>> commandPattern = new ArrayList<ArrayList<String>>();

    public StringReader(String s, int c) {
        this.string = s;
        this.cursor = c;
    }

    public StringReader(String s) {
        this.string = s;
        this.cursor = 0;
    }

    public void skip(int ski) {
        this.cursor += ski;
    }

    public void skip() {
        this.skip(1);
    }

    public boolean isWhiteSpace() {
        return Character.isWhitespace(this.read());
    }

    public boolean canRead(int next) {
        return this.cursor + next < this.string.length();
    }

    public boolean canRead() {
        return this.canRead(1);
    }

    public char read() {
        return this.string.toCharArray()[this.cursor];
    }

    public void skipWhiteSpace() {
        while (this.isWhiteSpace()) {
            this.skip();
        }
    }

    public boolean isEnd(int minus) {
       return this.cursor == this.string.length() - minus;
    }

    public boolean isSeparator(String separator) {
        char[] r = this.string.toCharArray();
        return r[this.cursor] == separator.toCharArray()[0];
    }

    public boolean isEnd() {
        return this.isEnd(0);
    }

    public void readWordsBetweenSpaces() {
        List<Character> characters = new ArrayList<Character>();
        while((this.canRead() || this.isEnd(1)) && !this.isWhiteSpace()) {
                char r = this.read();
                characters.add(r);
                skip();
        }
        if (this.isEnd() || this.isWhiteSpace()) {
            StringBuilder stringBuilder = new StringBuilder();
            characters.forEach(character -> stringBuilder.append(character));
            this.words.add(stringBuilder.toString());
            characters.clear();
            stringBuilder.delete(0, stringBuilder.toString().length());
            if (!(this.isEnd())) {
                this.skipWhiteSpace();
                if (this.canRead()) {
                    this.readWordsBetweenSpaces();
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> readCommandPatterns(String separator) {
        List<Character> singleCommand = new ArrayList<Character>();
        while((this.canRead() || this.isEnd(1)) && !this.isWhiteSpace() && !this.isSeparator(separator)) {
            char r = this.read();
            singleCommand.add(Character.valueOf(r));
            this.skip();
        }
        if (this.isEnd() || (this.isWhiteSpace() && !this.isSeparator(separator))) {
            StringBuilder stringBuilder = new StringBuilder();
            singleCommand.forEach(s -> stringBuilder.append(s));
            this.command.add(stringBuilder.toString());
            stringBuilder.delete(0, singleCommand.size());
            singleCommand.clear();
            if (!(this.isEnd())) {
                this.skipWhiteSpace();
                if (this.canRead()) {
                    this.readCommandPatterns(separator);
                }
            }
        }
        if (this.isEnd() || this.isSeparator(separator)) {
            this.commandPattern.add(new ArrayList<>(this.command));
            this.command.clear();
            if (!this.isEnd()) {
                this.readCommandPatterns(separator);
            }
        }
        return this.commandPattern = this.commandPattern.stream().filter(strings -> strings.size() != 0).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getWordsBetweenSpaces() {
        this.readWordsBetweenSpaces();
        return this.words;
    }
}
