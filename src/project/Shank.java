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
        if (args.length != 1) { //check for only 1 argument if not print following error message
            System.out.println("Error there should be only one argument ");
            return;
        }
        //pass in the file path of the file I want to read
        //used intellij to pass in the config of this : /Users/amadou/icsi-311/shankProject/shank.txt
        Path path = Paths.get(args[0]);
        try {
            List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<Token> tokenList = new ArrayList<>();
            var lexerTokens = new Lexer();
            for (String line : fileLines) {
                //print list of tokens
                List<Token>tokens = lexerTokens.lex(line);
                tokenList.addAll(tokens);
                System.out.println(tokens);

                //print out list of nodes from parser
                Parser Parser = new Parser((ArrayList<Token>) tokenList);
                Node root = Parser.parse();
               // Node functionNode = Parser.function();
                System.out.println(root.toString());
                //System.out.println(functionNode.toString());
                Interpreter Solve = new Interpreter();
                //System.out.println(AST.toString());

                System.out.println("The result of this expression is: " + Solve.Resolve(Parser.parse()));
            }

        } catch (IOException e) {
            System.out.println("An error occurred while trying to read the file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}