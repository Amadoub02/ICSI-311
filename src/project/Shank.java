package project;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Shank {
    public static void main(String[] args) {
        Path path = Paths.get("shank.txt");

        if (args.length == 0) {
            System.out.println("No argument was detected ");
        }
        if (args.length < 1) {
            System.out.println("More than one argument was detected ");
        } else {
            try {
                List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                List<Token> tokenList = new ArrayList<>();
                var lexer = new Lexer();
                for (String line: fileLines) {
                    //tokenList.addAll(lexer.lex(line));
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
