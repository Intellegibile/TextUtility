package me.intellegibile.textutility;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;
import me.intellegibile.textutility.operation.Operation;
import me.intellegibile.textutility.operation.PrintOperation;
import me.intellegibile.textutility.operation.DeleteOperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static boolean isReady() {
        System.out.println("Your file will be overwritten, write Y to continue or N to stop");
        boolean ready = false;
        Scanner scanner = new Scanner(System.in);
        String yn = scanner.next();
        String yes = "Y";
        String no = "N";
        if (yn.equals(yes)) {
            ready = true;
        } else if (yn.equals(no)) {
            ready = false;
        } else {
            System.out.println("Incorrect argument");
            isReady();
        }
        return ready;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: <operation> <arguments> <input> (Optional)<output>");
        }

        switch (args[0]) {
            case "print":
                if (args.length == 2) {
                    try {
                        if (args[1].endsWith(".txt")) {
                            PrintOperation printOperation = new PrintOperation(new InputFile(new FileInputStream(args[1])), null);
                            printOperation.operate();
                        }
                    } catch (FileNotFoundException e) {
                        e.getMessage();
                    }
                } else if (args.length > 2) {
                    if (args[2].endsWith(".txt")) {
                        throw new IllegalArgumentException("PrintOperation does not require an output file");
                    } else {
                        throw new IllegalArgumentException("You specified an existent argument");
                    }
                } else if (args.length < 2) {
                    throw new IllegalArgumentException("You need to define the path of a file to print");
                }
            case "delete":
                if (args.length == 4) {
                    try  {
                        if (args[2].endsWith(".txt") && args[3].endsWith(".txt")) {
                            DeleteOperation deleteOperation = new DeleteOperation(args[1], new InputFile(new FileInputStream(args[2])), new OutputFile(new FileOutputStream(args[3])));
                            deleteOperation.operate();
                        } else {
                            throw  new IllegalArgumentException("You need to define the path of the file input and output");
                        }
                    } catch (FileNotFoundException e) {
                        e.getMessage();
                    }
                } else if (args.length == 3) {
                    if (isReady()) {
                        try {
                            if (args[2].endsWith(".txt")) {
                                DeleteOperation deleteOperation = new DeleteOperation(args[1], new InputFile(new FileInputStream(args[2])), new OutputFile(new FileOutputStream(args[2])));
                                deleteOperation.operate();
                            } else {
                                throw new IllegalArgumentException("You need to define the path of the file input");
                            }
                        } catch (FileNotFoundException e) {
                            e.getMessage();
                        }
                    }
                } else if (args.length > 4) {
                    throw new IllegalArgumentException("You specified an argument not needed");
                } else {
                    throw new IllegalArgumentException("You specified less arguments");
                }
        }
    }
}
