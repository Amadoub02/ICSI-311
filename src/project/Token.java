package project;

public class Token {
    public enum TokenType {
        NUMBER, WORD, EndOfLine
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
