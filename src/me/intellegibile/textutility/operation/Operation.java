package me.intellegibile.textutility.operation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Operation {
    byte[] characters;
    FileInputStream input;
    FileOutputStream output;

    protected Operation(FileInputStream input_, FileOutputStream output_) {
        this.input = input_;
        this.output = output_;
        try {
            this.characters = new byte[input_.available()];
            input_.read(this.characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void operate();
}
