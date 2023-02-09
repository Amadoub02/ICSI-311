package project;

public class Token {
    public enum TokenType {
        NUMBER, WORD, EndOfLine,
        IDENTIFIER,STRINGLITERAL,CHARACTERLITERAL,
        WHILE,FOR,IF,BEGIN,END,REPEAT,DEFINE,
        INTEGER,CONSTANTS,FLOAT, REAL, VARIABLES,THEN,
        SEMICOLON, COLON, EQUALS, COMMA,COMMENT,
        LPAREN,RPAREN,WRITE
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
