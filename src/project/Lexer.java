package project;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    enum States{
        BEGIN,NUMBER,WORD,DECIMAL
    }


    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tempString = new StringBuilder();

        States state;
        state = States.BEGIN;
       // int state = 1;
        char currentChar;
        currentChar= 0;

        for (int i = 0; i < input.length(); i++) {

            currentChar = input.charAt(i);

            switch (state) {
                case BEGIN:
                    if (Character.isLetter(currentChar)) {
                        tempString.append(currentChar);
                        state = States.WORD;
                    }else if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = States.NUMBER;
                    }else if ( currentChar == '.') {
                        tempString.append(currentChar);
                        state = States.DECIMAL;
                    }else if(currentChar == ' '){
                        state = States.BEGIN;
                        tempString.setLength(0);
                    }else if(currentChar == '+' ||currentChar == '-' || currentChar == '*' || currentChar == '/'){
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else {
                    throw new Exception("Failed in state 1");
                    }
                    break;
                case  WORD:
                    if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.WORD, tempString.toString()));
                        tempString.append(currentChar);
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if(!Character.isLetter(currentChar) || !Character.isDigit(currentChar)){
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else {
                        throw new Exception("Failed in state 2");
                    }
                    break;
                case DECIMAL:
                     if (Character.isDigit(currentChar)){
                         tempString.append(currentChar);
                         state = States.DECIMAL;
                    }else if(!Character.isDigit(currentChar)) {
                         tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                         tempString.setLength(0);
                         state = States.BEGIN;
                     }else if(currentChar == ' ') {
                         tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                         tempString.setLength(0);
                         state = States.BEGIN;
                     }else {
                        throw new Exception("Failed in state 3");
                    }
                    break;
                case NUMBER:
                    if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if(!Character.isDigit(currentChar)) {
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = States.DECIMAL;
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.append(currentChar);
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else {
                        throw new Exception("Failed in state 4");
                    }
                    break;
            }

        }
        if(tempString.length() != 0) {
            char firstChar = tempString.charAt(0);
            if (Character.isLetter(firstChar)) {
                tokens.add(new Token(Token.TokenType.WORD, tempString.toString()));
            } else if (Character.isDigit(firstChar)) {
                tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
            }
        }
            tokens.add(new Token(Token.TokenType.EndOfLine, ""));
        return tokens;
    }
}
