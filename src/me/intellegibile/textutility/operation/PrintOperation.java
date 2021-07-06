package me.intellegibile.textutility.operation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class PrintOperation extends Operation{

    public PrintOperation(FileInputStream inputStream, FileOutputStream fileOutputStream) {
        super(inputStream, fileOutputStream);
    }

    public void operate() {
        System.out.println(new String(this.characters, StandardCharsets.UTF_8));
    }
}
