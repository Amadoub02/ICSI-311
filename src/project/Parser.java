package project;

import java.util.ArrayList;

public class Parser {

    private final ArrayList<Token> tokens;
    private Node root = null;
    //constructor that accepts tokens
    public Parser(ArrayList<Token> token) {
        this.tokens = token;
    }
    //method that parses the tokens
    //creat a public parse method that returns a node
    public Node parse() {
        root = Expression();
        return root;
    }

    public Token matchAndRemove(Token token) {
        Token temp = tokens.get(0);
        if (temp.getTokenValue() == token.getTokenValue()) {
            tokens.remove(0);
            return temp;
        } else {
            return null;
        }
    }

    private Node Term(){
        Node left = Factor();
        Node output = null;
        while(left != null){
            Token operator = matchAndRemove(new Token(Token.TokenType.MULTIPLY, "*"));
            if(operator == null){
                operator = matchAndRemove(new Token(Token.TokenType.DIVIDES, "/"));
                if(operator == null) {
                    operator = matchAndRemove(new Token(Token.TokenType.MODULUS, "mod"));
                    if (operator == null) {
                        break;
                    } else {
                        output = new MathOpNode(operator.getTokenValue(), left, Factor());
                    }
                }
            }
            else
            {
                output = new MathOpNode(operator.getTokenValue(), left, Factor());
            }
            left = output;
        }
        return left;
    }
    private Node Factor(){
        Token temp = matchAndRemove(new Token(Token.TokenType.NUMBER,"1"));
        if (temp == null) {
            temp = matchAndRemove(new Token(Token.TokenType.LEFTPAREN,"("));
            if (temp == null) {
                return null;
            } else {
                return Expression();
            }
        } else {
            /*put a way to test for a float number*/
            if (temp.getStringValue().contains("."))
            {   //if it is a float
                return new FloatNode(Float.parseFloat(temp.getStringValue()));
            }
            else
            {
                return new IntegerNode(Integer.parseInt(temp.getStringValue()));
            }
        }
    }
    private Node Expression() {
        Node left = Term();
        Node output = null;
        while (left != null) {
            Token operator = matchAndRemove(new Token(Token.TokenType.PLUS,"+"));
            if (operator == null) {
                operator = matchAndRemove(new Token(Token.TokenType.MINUS,"-"));
                if (operator == null) {
                    break;
                } else {
                    output = new MathOpNode(operator.getTokenValue(), left, Term());
                }
            } else {
                output = new MathOpNode(operator.getTokenValue(), left, Term());
            }
            left = output;
        }
        matchAndRemove(new Token(Token.TokenType.RIGHTPAREN, ")"));
        return left;
    }


}
