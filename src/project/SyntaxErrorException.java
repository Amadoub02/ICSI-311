package project;

public class SyntaxErrorException extends Exception{

    private String state;

    public SyntaxErrorException(String state) {
        this.state = state;
    }

    public String toString(){
        return "Syntax Error Exception " + state;
    }
}
