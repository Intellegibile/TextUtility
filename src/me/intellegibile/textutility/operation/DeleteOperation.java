package me.intellegibile.textutility.operation;

import java.io.*;
import java.util.ArrayList;

public class DeleteOperation extends Operation{
    private ArrayList<Character> first = new ArrayList<Character>();

    public DeleteOperation(String first_, FileInputStream input_, FileOutputStream output_) {
        super(input_, output_);
        for (char c : first_.toCharArray()) {
            first.add(c);
        }
    }

    public void delete() {
        while (this.isRunning()) {
            this.word(this.start);
            if (this.wordArray.equals(this.first)) {
                for (int l = this.startWord; l < this.finishWord; l++) {
                    this.charactersMedium.set(l, null);
                }
            }
            this.updateStart();
            this.clearWordArray();
        }
    }

    @Override
    public void operate() {
       this.delete();
       this.mediumToOutput();
       StringBuilder stringBuilder = new StringBuilder();
       for (int i = 0; i < this.charactersOutput.length; i++) {
           stringBuilder.append((char)this.charactersOutput[i]);
       }
       this.outputFile();
    }
}
