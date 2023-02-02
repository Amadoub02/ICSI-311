package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lexer {

    //Enums used to label states accordingly
    enum States{
        BEGIN,NUMBER,WORD,DECIMAL,IDENTIFIER
    }

    private HashMap<String, Token.TokenType > knownWords = new HashMap<>();
    {
        knownWords.put("while",Token.TokenType.WHILE);
        knownWords.put("if", Token.TokenType.IF);
        knownWords.put("for", Token.TokenType.FOR);
        knownWords.put("begin", Token.TokenType.BEGIN);
        knownWords.put("end", Token.TokenType.END);
    }
    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();



        boolean doWeHaveWhile = knownWords.containsKey("while");
        StringBuilder tempString = new StringBuilder(); //adds chars to string buffer

        States state;
        state = States.BEGIN;
        char currentChar;
        //currentChar= 0;

        //for loop that iterates over every character and assigns tokens based on conditions
        //inside for look is a state machine that changes states
        // based on the condition of current char and the result of the string "temp string"
        for (int i = 0; i < input.length(); i++) {

            currentChar = input.charAt(i);

            switch (state) {
                case BEGIN:
                    if (Character.isLetter(currentChar)) {
                        tempString.append(currentChar);
                        state = States.IDENTIFIER;
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
                case  IDENTIFIER:
                    if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.IDENTIFIER, tempString.toString()));
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
                    }else if (currentChar == '.') {
                        tempString.append(currentChar);
                        state = States.DECIMAL;
                    }else if(!Character.isDigit(currentChar)) {
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        state = States.BEGIN;
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
        //conditional to check if string is empty
        //fixes issue I had with assign tokens incorrectly
        if(tempString.length() != 0) {
            char firstChar = tempString.charAt(0);
            if (Character.isLetter(firstChar)) { //if string starts with a letter token assigned has to be a IDENTIFIER / identifier
                tokens.add(new Token(Token.TokenType.IDENTIFIER, tempString.toString())); //add according "IDENTIFIER" token
            } else if (Character.isDigit(firstChar)) { //if string starts with a digit, assigned token has to be a number
                tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));//add according "NUMBER" token
            }
        }
            //at the end of every input line, add an "EndOfLine" Token
            tokens.add(new Token(Token.TokenType.EndOfLine, ""));
        return tokens;
    }
}
