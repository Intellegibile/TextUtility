package me.intellegibile.textutility.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFile {
    private final FileInputStream fileInputStream;
    private byte[] bytes;
    private char[] chars;

    public InputFile(FileInputStream inputStream) {
        this.fileInputStream = inputStream;
    }

    public byte[] toByteArray(byte[] bytes) {
        try {
            this.fileInputStream.read(bytes);
        } catch (IOException e) {
            e.getMessage();
        }
        return bytes;
    }

    public char[] toCharArray() {
        try {
            this.bytes = new byte[fileInputStream.available()];
        } catch (IOException e) {
            e.getMessage();
        }
        this.toByteArray(this.bytes);
        List<Byte> byteList = new ArrayList<Byte>();
        for (int i = 0; i < this.bytes.length; i++) {
            byteList.add(bytes[i]);
        }
        List<Character> charactersList = new ArrayList<Character>();
        byteList.stream().forEach(aByte ->  charactersList.add(Character.valueOf((char)aByte.byteValue())));
        charactersList.stream().forEach(character -> this.chars[charactersList.indexOf(character)] = character.charValue());
        return this.chars;
    }
}
