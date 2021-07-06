package me.intellegibile.textutility;

import me.intellegibile.textutility.operation.Operation;
import me.intellegibile.textutility.operation.PrintOperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: <operation> <arguments> <input> (Optional)<output>");
        }

        switch (args[0]) {
            case "print":
                if (args.length == 2) {
                    try {
                        Operation printOperation = new PrintOperation(new FileInputStream(args[1]), null);
                        printOperation.operate();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (args.length > 2) {
                    throw new IllegalArgumentException("PrintOperation does not require an output argument");
                } else if (args.length < 2) {
                    throw new IllegalArgumentException("You need to define the path of a file to print");
                }
        }
    }

}
