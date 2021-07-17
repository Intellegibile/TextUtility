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

    @Override
    public void operate() {

    }
}
