package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lexer {
    private static boolean isCommenting = false;
    private static int indentLevel = 0;
    //Enums used to label states accordingly
    enum States{
        BEGIN,NUMBER,WORD,DECIMAL,IDENTIFIER,STRINGLITERAL,CHARACTERLITERAL,COMMENTS,SPACE,PUNCTUATION,OPERATION
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
        knownWords.put("mod", Token.TokenType.MODULUS);
        knownWords.put("left", Token.TokenType.LEFT);
        knownWords.put("right", Token.TokenType.RIGHT);
        knownWords.put("from", Token.TokenType.FROM);
        knownWords.put("array", Token.TokenType.ARRAY);
        knownWords.put("var", Token.TokenType.VAR);
        knownWords.put("substring", Token.TokenType.SUBSTRING);
        knownWords.put("sqr", Token.TokenType.SQUAREROOT);
        knownWords.put("intToreal", Token.TokenType.INTEGERTOREAL);
        knownWords.put("realtointeger", Token.TokenType.REALTOINTEGER);
        knownWords.put("readonly", Token.TokenType.READONLY);
        knownWords.put("alsoreadonly", Token.TokenType.ALSOREADONLY);
        knownWords.put("until", Token.TokenType.UNTIL);
        knownWords.put("boolean", Token.TokenType.BOOLEAN);
        knownWords.put("true", Token.TokenType.TRUE);
        knownWords.put("false", Token.TokenType.FALSE);
        knownWords.put("to", Token.TokenType.TO);
        knownWords.put("character", Token.TokenType.CHARACTER);
        knownWords.put("string", Token.TokenType.STRING);
        knownWords.put("else", Token.TokenType.ELSE);
        knownWords.put("elseif", Token.TokenType.ELSEIF);
        knownWords.put("of", Token.TokenType.OF);

    }
    private static final int INDENTATION_SIZE = 4;

    private static int getIndentationLevel(String line) {
        int indentLevel = 0;
        for (int i = 0; i < line.length(); i++) {
            char spaceChar = line.charAt(i);
            if (spaceChar == ' ') {
                indentLevel++;
            } else if (spaceChar == '\t') {
                indentLevel += INDENTATION_SIZE;
            } else {
                break;
            }
        }
        return indentLevel / INDENTATION_SIZE;
    }


    public List<Token> lex(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();

        String indentationLevel = String.valueOf(getIndentationLevel(input));

        int currentLevel = getIndentationLevel(input);
        if (currentLevel >= indentLevel && currentLevel != 0) {
            tokens.add(new Token(Token.TokenType.INDENT, indentationLevel));
        } else if (currentLevel < indentLevel) {
            tokens.add(new Token(Token.TokenType.DEDENT, indentationLevel));
        }
        indentLevel = currentLevel;

        StringBuilder tempString = new StringBuilder(); //adds chars to string buffer

        States state;
        state = States.BEGIN;
        char currentChar;

        if(isCommenting) {
            state = States.COMMENTS;
            tokens.removeAll(tokens);
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
                    }else if(currentChar == '+' ||currentChar == '-'){
                        tempString.append(currentChar);
                        state = States.NUMBER;
                    }else if(currentChar == '"'){
                        state = States.STRINGLITERAL;
                    }else if(currentChar == '\''){
                        state = States.CHARACTERLITERAL;
                    }else if(currentChar == '{'){
                        isCommenting = true;
                        state = States.COMMENTS;
                    }else if (currentChar == ';'){
                        tokens.add(new Token(Token.TokenType.SEMICOLON, ";"));
                        state = States.BEGIN;
                    }else if (currentChar == ':') {
                        if (input.charAt(i + 1) == '=') {
                        tokens.add(new Token(Token.TokenType.ASSIGNMENT, ":="));
                            i++;
                        }else {
                            tokens.add(new Token(Token.TokenType.COLON, ":"));
                        }
                    }else if (currentChar == '='){
                        tokens.add(new Token(Token.TokenType.EQUALS, "="));
                        state = States.BEGIN;
                    }else if (currentChar == ',') {
                        tokens.add(new Token(Token.TokenType.COMMA, ","));
                    }else if(currentChar == '('){
                        tokens.add(new Token(Token.TokenType.LEFTPAREN, "("));
                    }else if (currentChar == '<') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(Token.TokenType.LESSTHANEQUAL, "<="));
                            i++;
                        }else if(input.charAt(i + 1) == '>'){
                            tokens.add(new Token(Token.TokenType.NOTEQUAL, "<>"));
                            i++;
                        }else {
                            tokens.add(new Token(Token.TokenType.LESSTHAN, "<"));
                        }
                    }else if (currentChar == '>') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(Token.TokenType.GREATERTHANEQUAL, ">="));
                            i++;
                        }else {
                            tokens.add(new Token(Token.TokenType.GREATERTHAN, ">"));
                        }
                    }else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case  IDENTIFIER:
                    if (Character.isLetterOrDigit(currentChar)) {
                        tempString.append(currentChar);
                    }else if(currentChar == ','){
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.COMMA, ","));
                        state = States.BEGIN;
                    }else if(currentChar ==';'){
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.SEMICOLON, ";"));
                        state = States.BEGIN;
                    }else if(currentChar == '='){
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.EQUALS, "="));
                        state = States.BEGIN;
                    }else if (currentChar == ')'){
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.RIGHTPAREN, ")"));
                        state = States.BEGIN;
                    }else if (currentChar == ':') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.ASSIGNMENT, ":="));
                            i++;
                            state = States.BEGIN;
                        }else {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.COLON, ":"));
                            state = States.BEGIN;
                        }
                    }else if (currentChar == '<') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.LESSTHANEQUAL, "<="));
                            i++;
                            state = States.BEGIN;
                        }else if(input.charAt(i + 1) == '>'){
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.NOTEQUAL, "<>"));
                            i++;
                            state = States.BEGIN;
                        }else {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.LESSTHAN, "<"));
                            state = States.BEGIN;
                        }
                    }else if (currentChar == '>') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.GREATERTHANEQUAL, ">="));
                            i++;
                            state = States.BEGIN;
                        }else {
                            tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.GREATERTHAN, ">"));
                            state = States.BEGIN;
                        }
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                        tempString.setLength(0);
                        state = States.SPACE;
                    }else if(!Character.isLetterOrDigit(currentChar)){
                        state = States.BEGIN;
                        tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString()));
                    }else {
                        throw new Exception("Failed in identifier state");
                    }
                    break;
                case DECIMAL:
                     if (Character.isDigit(currentChar)){
                         tempString.append(currentChar);
                         state = States.NUMBER;
                    }else if(currentChar == ' ') {
                         tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                         tempString.setLength(0);
                         state = States.SPACE;
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
                    }else if(currentChar == ')'){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.RIGHTPAREN,")"));
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if(currentChar == '+'){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.PLUS,"+"));
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if(currentChar == '-'){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.MINUS, "-"));
                        tempString.setLength(0);
                        state = States.BEGIN;
                    }else if(currentChar == '*'){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.MULTIPLY, "*"));
                        state = States.BEGIN;
                    }else if(currentChar == '/'){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.DIVIDES, "/"));
                        state = States.BEGIN;
                    }else if(currentChar == '='){
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        tokens.add(new Token(Token.TokenType.EQUALS, "="));
                        state = States.BEGIN;
                    }else if (currentChar == '<') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.LESSTHANEQUAL, "<="));
                            i++;
                            state = States.BEGIN;
                        }else if(input.charAt(i + 1) == '>'){
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.NOTEQUAL, "<>"));
                            i++;
                            state = States.BEGIN;
                        }
                    }else if (currentChar == '>') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.GREATERTHANEQUAL, ">="));
                            i++;
                            state = States.BEGIN;
                        }else {
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.GREATERTHAN, ">"));
                            state = States.BEGIN;
                        }
                    }else if (currentChar == ':') {
                        if (input.charAt(i + 1) == '=') {
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.ASSIGNMENT, ":="));
                            i++;
                            state = States.BEGIN;
                        }else{
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.COLON, ":"));
                            state = States.BEGIN;
                        }
                    }else if (currentChar == ' ') {
                        tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                        tempString.setLength(0);
                        state = States.SPACE;
                    } else if(!Character.isDigit(currentChar)){

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
                    if(currentChar != '}'){
                        isCommenting = true;
                        state = States.COMMENTS;
                    }
                    if(currentChar == '}'){
                        state = States.BEGIN;
                        isCommenting = false;
                        tempString.setLength(0);
                    }
                    break;
                    case SPACE:
                        if (Character.isLetter(currentChar)) {
                            tempString.append(currentChar);
                            state = States.IDENTIFIER;
                        }else if(Character.isDigit(currentChar)){
                            tempString.append(currentChar);
                            state = States.NUMBER;
                        }else if(currentChar == '('){
                            tokens.add(new Token(Token.TokenType.LEFTPAREN,"("));
                            tempString.setLength(0);
                            state = States.BEGIN;
                        }else if(currentChar == '+'){
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.PLUS,"+"));
                            tempString.setLength(0);
                            state = States.BEGIN;
                        }else if(currentChar == '-'){
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.MINUS, "-"));
                            tempString.setLength(0);
                            state = States.BEGIN;
                        }else if(currentChar == '*'){
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.MULTIPLY, "*"));
                            state = States.BEGIN;
                        }else if(currentChar == '/'){
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.DIVIDES, "/"));
                            state = States.BEGIN;
                        }else if(currentChar == '='){
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.EQUALS, "="));
                            state = States.BEGIN;
                        }else if(currentChar == ')'){
                            tokens.add(new Token(Token.TokenType.NUMBER, tempString.toString()));
                            tempString.setLength(0);
                            tokens.add(new Token(Token.TokenType.RIGHTPAREN,")"));
                            tempString.setLength(0);
                            state = States.BEGIN;
                        }else if (currentChar == '>') {
                            if (input.charAt(i + 1) == '=') {
                                tokens.add(new Token(Token.TokenType.GREATERTHANEQUAL, ">="));
                                i++;
                                state = States.BEGIN;
                            }else {
                                tokens.add(new Token(Token.TokenType.GREATERTHAN, ">"));
                                state = States.BEGIN;
                            }
                            }else if (currentChar == '<') {
                            if (input.charAt(i + 1) == '=') {
                                tokens.add(new Token(Token.TokenType.LESSTHANEQUAL, "<="));
                                i++;
                                state = States.BEGIN;
                            }else if(input.charAt(i + 1) == '>'){
                                tokens.add(new Token(Token.TokenType.NOTEQUAL, "<>"));
                                i++;
                                state = States.BEGIN;
                            }else {
                                tokens.add(new Token(Token.TokenType.GREATERTHAN, ">"));
                                state = States.BEGIN;
                            }
                            }else if (currentChar == ':') {
                            if (input.charAt(i + 1) == '=') {
                                tokens.add(new Token(Token.TokenType.ASSIGNMENT, ":="));
                                i++;
                                state = States.BEGIN;
                            }else {
                                tokens.add(new Token(Token.TokenType.COLON, ":"));
                            }
                        }else if (currentChar == ',') {
                            tokens.add(new Token(Token.TokenType.COMMA, ","));
                        }else if(currentChar == ' '){
                            state = States.BEGIN;
                        }else {
                            throw new SyntaxErrorException();
                        }
            }

        }

        //conditional to check if string is empty
        //fixes issue I had with assign tokens incorrectly
        if(tempString.length() != 0) {
            char firstChar = tempString.charAt(0);
            if (Character.isLetter(firstChar)) { //if string starts with a letter token assigned has to be a IDENTIFIER / identifier
                tokens.add(new Token(getTypeFromMap(tempString.toString()), tempString.toString())); //add according "IDENTIFIER" token
            } else if (Character.isDigit(firstChar) || firstChar == '.') { //if string starts with a digit, assigned token has to be a number
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
