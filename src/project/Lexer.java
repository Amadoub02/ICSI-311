package project;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tempString = new StringBuilder();

        int state = 1;
        for (int i = 0; i < input.length(); i++) {

            char currentChar = 0;
            currentChar = input.charAt(i);

            switch (state) {
                case 1:
                    if (Character.isLetter(currentChar)) {
                        state = 2;
                    } else if (Character.isDigit(currentChar)) {
                        state = 4;
                    } else if ( currentChar == '.') {
                        state = 3;
                    }
                    break;
                case  2:
                    if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        tokens.add(new Token(Token.Tokens.WORD, tempString.toString()));
                        //tempString.setLength(0);
                        tempString.append(currentChar);
                    } else {
                        state = 1;
                }
                    break;
                case 3:
                    if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = 1;
                    }else {
                        tempString.append(currentChar);
                        tokens.add(new Token(Token.Tokens.NUMBER, tempString.toString()));

                    }
                case 4:
                    if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = 1;
                    } else if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }else {
                        state = 1;
                    }
            }
        }
        return tokens;
    }
}
