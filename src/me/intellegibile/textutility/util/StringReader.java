package me.intellegibile.textutility.util;

import java.util.*;

public class StringReader {
    private final String string;
    private int cursor;
    private List<String> words = new ArrayList<String>();

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

    public List<String> getWordsBetweenSpaces() {
        this.readWordsBetweenSpaces();
        return this.words;
    }
}
