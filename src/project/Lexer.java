package project;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tempString = new StringBuilder();

        int state = 1;
        char currentChar;

        for (int i = 0; i < input.length(); i++) {

            currentChar = input.charAt(i);

            switch (state) {
                case 1:
                    if (Character.isLetter(currentChar)) {
                        tempString.append(currentChar);
                        state = 2;
                    } else if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = 4;
                    } else if ( currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }/**else {
                        throw new Exception("Failed in state 1");
                    }**/
                    break;
                case  2:
                    if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = 2;
                    } else {
                        tokens.add(new Token(Token.Tokens.WORD,tempString.toString()));
                        state = 1;
                    }

                    break;
                case 3:
                    if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }else if (Character.isDigit(currentChar)){
                        state = 3;
                        tempString.append(currentChar);
                    }
                    break;
                case 4:
                    if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        tokens.add(new Token(Token.Tokens.NUMBER, tempString.toString()));
                        state = 4;
                    } else if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }
                    break;
            }
        }
        return tokens;
    }
}
