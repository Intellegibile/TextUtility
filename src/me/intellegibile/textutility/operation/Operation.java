package me.intellegibile.textutility.operation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public abstract class Operation {
    protected byte[] charactersInput;
    protected byte[] charactersOutput;
    private boolean run = true;
    protected ArrayList<Byte> charactersMedium = new ArrayList<Byte>();
    protected FileInputStream input;
    protected FileOutputStream output;
    protected int start = 0;
    protected int startWord = 0;
    protected int finishWord;
    protected ArrayList<Character> wordArray = new ArrayList<Character>();

    protected Operation(FileInputStream input_, FileOutputStream output_) {
        this.input = input_;
        this.output = output_;
        if (input_ != null) {
            try {
                this.charactersInput = new byte[input_.available()];
                input_.read(this.charactersInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.startCharacterMedium();
    }

    public abstract void operate();

    public void outputFile() {
        try {
            this.output.write(this.charactersOutput);
            this.output.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void updateStart() {
        this.startWord = this.start;
    }

    public void mediumToOutput() {
        this.charactersMedium = new ArrayList<>(this.charactersMedium.stream().filter(new Predicate<Byte>() {
            @Override
            public boolean test(Byte aByte) {
                return aByte != null;
            }
        }).toList());
        this.charactersOutput = new byte[this.charactersMedium.size()];
        for (int i = 0; i < this.charactersMedium.size(); i++) {
            this.charactersOutput[i] = this.charactersMedium.get(i);
        }
    }

    public void startCharacterMedium() {
        for (int e = 0; e < this.charactersInput.length; e++) {
            this.charactersMedium.add(e, this.charactersInput[e]);
        }
    }

    public void clearWordArray() {
        this.wordArray.clear();
    }

    public void restart() {
        this.run = true;
    }

    public boolean isRunning() {
        return run;
    }

    public void setStart(int start) {
        for (int k = start; k < this.charactersInput.length; k++) {
            if (this.charactersInput[k] != 32) {
                this.start = k;
                break;
            }
        }
    }

    public void word(int start_) {
        for (int i = start_; i < this.charactersInput.length; i++) {
            boolean flag = i == this.charactersInput.length - 1;
            if (this.charactersInput[i] != 32) {
                this.wordArray.add((char) this.charactersInput[i]);
            }
            if (this.charactersInput[i] == 32 || flag) {
                if (this.wordArray.size() != 0) {
                    this.finishWord = i;
                }
                if (flag) {
                    this.finishWord = i + 1;
                }
                if (flag) {
                    this.run = false;
                }
                this.setStart(i);
                break;
            }
            else if (i != this.charactersInput.length - 1) {
                this.restart();
            }
        }
    }
}
