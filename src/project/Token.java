package project;

public class Token {
    public enum TokenType {
        NUMBER, WORD, EndOfLine,INDENT,DEDENT,
        IDENTIFIER,STRINGLITERAL,CHARACTERLITERAL,
        WHILE,FOR,IF,BEGIN,END,REPEAT,DEFINE,
        INTEGER,CONSTANTS,FLOAT, REAL, VARIABLES,THEN,
        SEMICOLON, COLON, EQUALS, COMMA,COMMENT,
        LEFTPAREN,RIGHTPAREN,WRITE,GREATERTHAN,LESSTHAN,GREATERTHANEQUAL,LESSTHANEQUAL,
        PLUS, MINUS, DIVIDES,MULTIPLY,MODULUS,NOTEQUAL, ASSIGNMENT,
        LEFT,RIGHT,FROM,ARRAY,VAR,SUBSTRING,SQUAREROOT,INTEGERTOREAL,REALTOINTEGER,READONLY,ALSOREADONLY,UNTIL,
        BOOLEAN,TRUE,FALSE,TO,CHARACTER,STRING,ELSE,ELSEIF,OF
    }

    private TokenType tokenValue;
    private String stringValue;

    public Token(TokenType tokenValue, String stringValue) {
        this.tokenValue = tokenValue;
        this.stringValue = stringValue;
    }
    public TokenType getTokenValue() {
        return tokenValue;
    }
    public String getStringValue() {
        return stringValue;
    }
    public String toString(){
        return tokenValue + "(" + stringValue + ")" ;
    }


}
