package me.intellegibile.textutility.Pattern;

import me.intellegibile.textutility.file.InputFile;
import me.intellegibile.textutility.file.OutputFile;
import me.intellegibile.textutility.operation.DeleteOperation;
import me.intellegibile.textutility.operation.PrintOperation;
import me.intellegibile.textutility.util.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;

public class CommandPattern {
    private final String[] argoments;
    private String PATTERN_SEPARATOR = "|";
    private String NONE = "none";
    private StringReader commandParser;
    private ArrayList<ArrayList<String>> commandPattern = new ArrayList<ArrayList<String>>();

    public CommandPattern(String[] args) {
        this.argoments = args;
        this.commandParser = this.toStringReader();
        this.commandPattern = this.commandParser.readCommandPatterns(this.PATTERN_SEPARATOR);
    }

    public boolean isPatternSeparator(String string) {
        return string == this.PATTERN_SEPARATOR;
    }

    public StringReader toStringReader() {
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < this.argoments.length; i++) {
            if (i == this.argoments.length - 1) {
                builder.append(this.argoments[i]);
            } else {
                builder.append(this.argoments[i] + " ");
            }
        }
        StringReader reader = new StringReader(builder.toString());
        return reader;
    }

    public boolean isNone(String string) {
        return string.equals(this.NONE);
    }

    public void parseDeleteCommand(ArrayList<String> command, boolean inputAndOutput, boolean inputOutput, Path input, Path output) {
        if (!this.isNone(command.get(1)) && !command.get(1).equals("space")) {
            if (inputAndOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(output));
                deleteOperation.operate(DeletePattern.DELETE_SPECIFIC_WORD);
            } else if (inputOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(input));
                deleteOperation.operate(DeletePattern.DELETE_SPECIFIC_WORD);
            }
        } else if (this.isNone(this.argoments[3])) {
            if (inputAndOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(output));
                deleteOperation.operate(DeletePattern.DELETE_EVERYTHING);
            } else if (inputOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(input));
                deleteOperation.operate(DeletePattern.DELETE_EVERYTHING);
            }
        } else {
            if (inputAndOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(output));
                deleteOperation.operate(DeletePattern.DELETE_ALL_SPACES);
            } else if (inputOutput) {
                DeleteOperation deleteOperation = new DeleteOperation(command.get(1), new InputFile(input), new OutputFile(input));
                deleteOperation.operate(DeletePattern.DELETE_ALL_SPACES);
            }
        }
    }

    public void parsePrintCommand(boolean inputAndOutput, boolean inputOutput, Path input, Path output) {
        if (inputAndOutput) {
            PrintOperation printOperation = new PrintOperation(new InputFile(input), new OutputFile(output));
            printOperation.operate(PrintPattern.PRINT);
        } else if (inputOutput) {
            PrintOperation printOperation = new PrintOperation(new InputFile(input), new OutputFile(null));
            printOperation.operate(PrintPattern.PRINT);
        }
    }

    public void parse() {
        Path input;
        Path output;
        boolean inputOutput;
        boolean inputAndOutput;
        if (this.argoments[0].endsWith(".txt") && this.argoments[1].endsWith(".txt")) {
            input = Path.of(argoments[0]);
            output = Path.of(argoments[1]);
            inputAndOutput = true;
            inputOutput = false;
        } else if (this.argoments[0].endsWith(".txt") && this.argoments[1].equals("none")) {
            input = Path.of(argoments[1]);
            output = null;
            inputOutput = true;
            inputAndOutput = false;
        } else {
            throw new IllegalArgumentException("You need to define an input-output txt file or an input and a different output file");
        }
        for (ArrayList<String> command : this.commandPattern) {
            switch (command.get(0)) {
                case "print":
                    this.parsePrintCommand(inputAndOutput, inputOutput, input, output);
                    break;
                case "delete":
                    this.parseDeleteCommand(command, inputAndOutput, inputOutput, input, output);
                    break;
            }
        }
    }
}
