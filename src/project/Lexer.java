package project;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tempString = new StringBuilder();

        int state = 1;
        char currentChar;
        currentChar= 0;

        for (int i = 0; i < input.length(); i++) {

            currentChar = input.charAt(i);

            switch (state) {
                case 1:
                    if (Character.isLetter(currentChar)) {
                        tempString.append(currentChar);
                        state = 2;
                    }else if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                        state = 4;
                    }else if ( currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }else if(currentChar == ' '){
                        state = 1;
                    }else if(currentChar == '+' ||currentChar == '-' || currentChar == '*' || currentChar == '/'){
                        state = 1;
                    }else {
                    throw new Exception("Failed in state 1");
                    }
                    break;
                case  2:
                    if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.WORD, tempString.toString()));
                        tempString.append(currentChar);
                        tempString.setLength(0);
                        state = 1;
                    }else if(!Character.isLetter(currentChar) || !Character.isDigit(currentChar)){
                        tempString.setLength(0);
                        state = 1;
                    }else {
                        throw new Exception("Failed in state 2");
                    }
                    break;
                case 3:
                     if (Character.isDigit(currentChar)){
                         tempString.append(currentChar);
                         state = 3;
                    }else if(!Character.isDigit(currentChar)) {
                         tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                         tempString.setLength(0);
                         state = 1;
                     }else if(currentChar == ' ') {
                         tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                         tempString.setLength(0);
                         state = 1;
                     }else {
                        throw new Exception("Failed in state 3");
                    }
                    break;
                case 4:
                    if (Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                    } else if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = 3;
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.append(currentChar);
                        tempString.setLength(0);
                        state = 1;
                    }else {
                        throw new Exception("Failed in state 4");
                    }
            }
        }
        if(Character.isLetter(currentChar)) {
            tokens.add(new Token(Token.TokenType.WORD, tempString.toString()));
        }
        else if (Character.isDigit(currentChar)) {
            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
        }
        if(tempString.length() > 0){
            tokens.add(new Token(Token.TokenType.EndOfLine, ""));
        }
        if(input.isEmpty()){
            tokens.add(new Token(Token.TokenType.EndOfLine, ""));
        }
        return tokens;
    }
}
