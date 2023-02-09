package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lexer {
    private static boolean isCommenting = false;
    //Enums used to label states accordingly
    enum States{
        BEGIN,NUMBER,WORD,DECIMAL,IDENTIFIER,STRINGLITERAL,CHARACTERLITERAL,COMMENTS,SPACE,PUNCTUATION
    }
    public HashMap<String, Token.TokenType> knownWords = new HashMap<>(); //Hashmap to hold keywords
    {
        knownWords.put("while",Token.TokenType.WHILE);
        knownWords.put("if", Token.TokenType.IF);
        knownWords.put("then",Token.TokenType.THEN);
        knownWords.put("for", Token.TokenType.FOR);
        knownWords.put("begin", Token.TokenType.BEGIN);
        knownWords.put("end", Token.TokenType.END);
        knownWords.put("repeat", Token.TokenType.REPEAT);
        knownWords.put("define", Token.TokenType.DEFINE);
        knownWords.put("real", Token.TokenType.REAL);
        knownWords.put("integer", Token.TokenType.INTEGER);
        knownWords.put("variables", Token.TokenType.VARIABLES);
        knownWords.put("constants", Token.TokenType.CONSTANTS);
        knownWords.put("float", Token.TokenType.FLOAT);
        knownWords.put("write", Token.TokenType.WRITE);
    }
    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        StringBuilder tempString = new StringBuilder(); //adds chars to string buffer

        States state;
        state = States.BEGIN;
        char currentChar;

        if(isCommenting) {
            state = States.COMMENTS;
        }

        //for loop that iterates over every character and assigns tokens based on conditions
        //inside for loop is a state machine that changes states
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
                    }else if(currentChar == '"'){
                        //tempString.append(currentChar);
                        state = States.STRINGLITERAL;
                    }else if(currentChar == '\''){
                        //tempString.append(currentChar);
                        state = States.CHARACTERLITERAL;
                    }else if(currentChar == '{'){
                        isCommenting = true;
                        //tempString.append(currentChar);
                        state = States.COMMENTS;
                    }else if(currentChar == ';' || currentChar == ':' || currentChar == '='){
                        state = States.PUNCTUATION;
                    }else{
                    throw new Exception("Failed in begin state");
                    }
                    break;
                case  IDENTIFIER:
                    if (Character.isLetterOrDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if(!Character.isLetterOrDigit(currentChar)){
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else {
                        throw new Exception("Failed in identifier state");
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
                        throw new Exception("Failed in decimal state");
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
                        throw new Exception("Failed in number state");
                    }
                    break;
                case STRINGLITERAL:
                    if(currentChar != '"'){
                        tempString.append(currentChar);
                    }
                    if(currentChar == '"'){
                        tokens.add(new Token(Token.TokenType.STRINGLITERAL,tempString.toString()));
                        tempString.setLength(0);
                        state = state.BEGIN;
                    }
                    break;
                case CHARACTERLITERAL:
                    if(currentChar != '\''){
                        tempString.append(currentChar);
                    }
                    if(currentChar == '\'' && tempString.length() == 1){
                        tokens.add(new Token(Token.TokenType.CHARACTERLITERAL,tempString.toString()));
                        tempString.setLength(0);
                        state = state.BEGIN;
                    }
                    break;
                case COMMENTS:
                    if(currentChar != '{'){
                        isCommenting = true;
                        state = States.COMMENTS;
                    }
                    if(currentChar == '}'){
                        state = States.BEGIN;
                        isCommenting = false;
                        tempString.setLength(0);
                    }
                    break;
                case PUNCTUATION:
                    if(currentChar == ';') {
                        tokens.add(new Token(Token.TokenType.SEMICOLON,tempString.toString()));
                        state = States.BEGIN;

                    }else if (currentChar == ':') {
                        tokens.add(new Token(Token.TokenType.COLON, tempString.toString()));
                    }else if(currentChar == '=') {
                        tokens.add(new Token(Token.TokenType.EQUALS, "="));
                    }
            }

        }

        //conditional to check if string is empty
        //fixes issue I had with assign tokens incorrectly
        if(tempString.length() != 0) {
            char firstChar = tempString.charAt(0);
            if (Character.isLetter(firstChar)) { //if string starts with a letter token assigned has to be a IDENTIFIER / identifier
                tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString())); //add according "IDENTIFIER" token
            } else if (Character.isDigit(firstChar)) { //if string starts with a digit, assigned token has to be a number
                tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));//add according "NUMBER" token
            }
        }

            //at the end of every input line, add an "EndOfLine" Token
            tokens.add(new Token(Token.TokenType.EndOfLine, ""));
        return tokens;
    }
    public Token.TokenType getTypeFromMap(String value){
        Token.TokenType type = knownWords.get(value);
        if (type != null) {
            return type;
        }else {
        return Token.TokenType.IDENTIFIER;
        }
    }
}
