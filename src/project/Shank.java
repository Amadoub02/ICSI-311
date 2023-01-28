package project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Shank {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error there should be only one argument ");
            return;
        }
        Path path = Paths.get(args[0]);
        //Path path = Paths.get("shank.txt");
        try {
            List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<Token> tokenList = new ArrayList<>();
            var lexerTokens = new Lexer();
            for (String line : fileLines) {
                tokenList.addAll(lexerTokens.lex(line));
                System.out.println(line);
            }
            String printTokens = "";
            for(var token: tokenList) {
                System.out.println(token.toString());
            }
            System.out.println(printTokens);
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read the file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}