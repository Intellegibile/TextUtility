package me.intellegibile.textutility;

import me.intellegibile.textutility.Pattern.CommandPattern;

public class Main {

    public static void main(String[] args) {
        CommandPattern commandPattern = new CommandPattern(args);
        commandPattern.parse();
    }

}
