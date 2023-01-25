package project;

public class Token {
    enum Tokens {
        NUMBER, WORD, EndOfLine
    }

    private Tokens tokenValue;
    private String stringValue;

    public Token(Tokens tokenValue, String stringValue) {
        this.tokenValue = tokenValue;
        this.stringValue = stringValue;
    }
    public Tokens getTokenValue() {
        return tokenValue;
    }
    public String getStringValue() {
        return stringValue;
    }
    public String toString(){
        return stringValue + " " + tokenValue;
    }


}
