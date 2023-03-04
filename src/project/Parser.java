package project;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final ArrayList<Token> tokens;
    private int currentToken = 0;
    private Node root = null;
    //constructor that accepts tokens
    public Parser(ArrayList<Token> token) {
        this.tokens = token;
        this.currentToken = 0;
    }
    //method that parses the tokens
    //creat a public parse method that returns a node
    public Node parse() throws SyntaxErrorException {
        root = Expression();
        expectEndsOfLine();
        return root;
    }

    public Token matchAndRemove(Token.TokenType tokenType) {
        Token temp = tokens.get(0);
        if (temp.getTokenType() == tokenType) {
            tokens.remove(0);
            currentToken++;
            return temp;
        } else {
            return null;
        }
    }
    private Token peek(int n) {
        if (currentToken + n >= tokens.size()) {
            return null;
        }
        return tokens.get(currentToken + n);
    }
    private parameterDeclarations(){
        String name = matchAndRemove(Token.TokenType.IDENTIFIER).getStringValue();
        return VariableNode();
    }

    public void expectEndsOfLine() throws SyntaxErrorException {
        // If there are no tokens, throw an exception
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("Expected end of line.");
        }
        // Remove any EndOfLine tokens from the end of the list
        while (tokens.size() > 0 && tokens.get(tokens.size() - 1).getTokenType() == Token.TokenType.EndOfLine) {
            tokens.remove(tokens.size() - 1);
        }
    }

    private Node Term(){
        Node left = Factor();
        Node output = null;
        while(left != null){
            Token operator = matchAndRemove(Token.TokenType.MULTIPLY);
            if(operator == null){
                operator = matchAndRemove(Token.TokenType.DIVIDE);
                if(operator == null) {
                    operator = matchAndRemove(Token.TokenType.MODULUS);
                    if (operator == null) {
                        break;
                    } else {
                        output = new MathOpNode(operator.getTokenType(), left, Factor());
                    }
                }
            }
            else
            {
                output = new MathOpNode(operator.getTokenType(), left, Factor());
            }
            left = output;
        }
        return left;
    }
    private Node Factor(){
        Token temp = matchAndRemove(Token.TokenType.NUMBER);
        if (temp == null) {
            temp = matchAndRemove(Token.TokenType.LEFTPAREN);
            if (temp == null) {
                return null;
            } else {
                return Expression();
            }
        } else {
            if (temp.getStringValue().contains("."))
            {
                return new RealNode(Float.parseFloat(temp.getStringValue()));
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
            Token operator = matchAndRemove(Token.TokenType.PLUS);
            if (operator == null) {
                operator = matchAndRemove(Token.TokenType.MINUS);
                if (operator == null) {
                    break;
                } else {
                    output = new MathOpNode(operator.getTokenType(), left, Term());
                }
            } else {
                output = new MathOpNode(operator.getTokenType(), left, Term());
            }
            left = output;
        }
        matchAndRemove(Token.TokenType.RIGHTPAREN);
        return left;
    }
   public FunctionNode function() throws Exception {
        String functionName = null;
        List<VariableNode> functionParameters = new ArrayList<VariableNode>();
        List<VariableNode> functionVariables = new ArrayList<VariableNode>();
        List<VariableNode> functionConstants = new ArrayList<VariableNode>();
        List<VariableNode> functionStatements = new ArrayList<VariableNode>();


        if(matchAndRemove(Token.TokenType.DEFINE) != null){
            if(matchAndRemove(Token.TokenType.IDENTIFIER) == null) throw new Exception("Missing function identifer");
            if(matchAndRemove(Token.TokenType.LEFTPAREN) == null) throw new Exception("Missing left parenthesis");

            functionParameters = parameterDeclarations();

            if(matchAndRemove(Token.TokenType.RIGHTPAREN) == null) throw new Exception("Missing right parenthesis");

        }

       if(tokens.get(0).getTokenType() == Token.TokenType.LEFTPAREN) {
           matchAndRemove(Token.TokenType.LEFTPAREN);
       }
       if(tokens.get(0).getTokenType() == Token.TokenType.IDENTIFIER){

       }


        return null;
    }


}
