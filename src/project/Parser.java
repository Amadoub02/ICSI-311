package project;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final ArrayList<Token> tokens;
    ArrayList<StatementNode> statements = new ArrayList<>();
    ArrayList<StatementNode> body = new ArrayList<>();
    private int currentToken = 0;
    private Node root = null;
    //constructor that accepts tokens
    public Parser(ArrayList<Token> token) {
        this.tokens = token;
        this.currentToken = 0;
    }
    //method that parses the tokens
    //creat a public parse method that returns a node
    public Node parse() throws Exception {
        root = Expression();
        expectEndsOfLine();

        //return new ProgramNode();
       //FunctionNode functionNode = function();

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
    private List<VariableNode> parameterDeclarations() throws SyntaxErrorException{
        List<VariableNode> parameters = new ArrayList<>();
        List<String> varNames = new ArrayList<>();
        VariableNode.varType type;
        while(!tokens.isEmpty() && tokens.get(0).getTokenType() == Token.TokenType.IDENTIFIER) {
            varNames.add(tokens.remove(0).getStringValue());
            matchAndRemove(Token.TokenType.COMMA);
        }
        if(matchAndRemove(Token.TokenType.COLON)== null) throw new SyntaxErrorException("Missing colon");

        Token.TokenType tokenType = tokens.remove(0).getTokenType();
        if(tokenType == Token.TokenType.INTEGER){
            type = VariableNode.varType.INTEGER;
        } else if (tokenType == Token.TokenType.REAL){
            type = VariableNode.varType.REAL;
        } else {
            throw new SyntaxErrorException("Invalid Input");
        }

        for(int i = 0; i < varNames.size(); i++) {
            parameters.add(new VariableNode(type, varNames.get(i)));
        }
        //semicolon means there are more params so if there is one add the current params
        if (matchAndRemove(Token.TokenType.SEMICOLON)!= null)
            parameters.addAll(parameterDeclarations());
        return parameters;
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

    public BooleanCompareNode booleanCompare() {
        try {
            expectEndsOfLine();
            Node left = Expression();
            Token.TokenType operatorType = peek(0).getTokenType();
            if (operatorType == Token.TokenType.EQUALS || operatorType == Token.TokenType.NOTEQUAL ||
                    operatorType == Token.TokenType.LESSTHAN || operatorType == Token.TokenType.GREATERTHAN ||
                    operatorType == Token.TokenType.LESSTHANEQUAL || operatorType == Token.TokenType.GREATERTHANEQUAL) {

                matchAndRemove(operatorType); // remove the operator token from the list of tokens

                Node right = Expression();

                return new BooleanCompareNode(left, right, operatorType);
            } else {
                return new BooleanCompareNode(left, null, null);
            }
        } catch (Exception e) {
            System.out.println("Not an expression");
            throw new RuntimeException(e);
        }
    }

    public FunctionNode function() throws Exception {
        String functionName = null;
        List<VariableNode> functionParameters = new ArrayList<>();
        List<VariableNode> functionVariables = new ArrayList<>();
        List<VariableNode> functionConstants = new ArrayList<>();
        List<StatementNode> functionStatements = new ArrayList<>();


        if (matchAndRemove(Token.TokenType.DEFINE) == null){
            return null;
        }

        // Expect an identifier for the function name
        Token functionNameToken = matchAndRemove(Token.TokenType.IDENTIFIER);
        if (functionNameToken == null) {
            throw new Exception("Missing identifier");
        }
        functionName = functionNameToken.getStringValue();

        // Expect a left parenthesis
        if (matchAndRemove(Token.TokenType.LEFTPAREN) == null) {
            throw new Exception("Missing left parenthesis");
        }

        // Parse the list of function parameters
        functionParameters = parameterDeclarations();

        // Expect a right parenthesis
       if (matchAndRemove(Token.TokenType.RIGHTPAREN) == null) {
            throw new Exception("Missing right parenthesis");
        }

        // Expect an end of line token
        expectEndsOfLine();

        // Parse the constants and variables
        while (!tokens.isEmpty() && (tokens.get(0).getTokenType() == Token.TokenType.CONSTANTS
                || tokens.get(0).getTokenType() == Token.TokenType.VAR)) {
            Token.TokenType varType = matchAndRemove(tokens.get(0).getTokenType()).getTokenType();
            if (varType == Token.TokenType.CONSTANTS) {
                functionConstants.addAll(parameterDeclarations());
            } else {
                functionVariables.addAll(parameterDeclarations());
            }
        }

        // Expect an indent token
        if (matchAndRemove(Token.TokenType.INDENT) == null) {
            throw new Exception("Missing indent token");
        }

        // Parse the statements
        while (!tokens.isEmpty() && tokens.get(0).getTokenType() != Token.TokenType.DEDENT) {
            functionStatements.add((StatementNode) Expression());
            expectEndsOfLine();
        }

        // Expect a dedent token
        if (matchAndRemove(Token.TokenType.INDENT) == null) {
            throw new Exception("Missing dedent token");
        }

        // Create and return a new FunctionNode
        return new FunctionNode(functionName, functionParameters, functionVariables,
                functionConstants, functionStatements);
    }

  public List<VariableNode> constants() throws SyntaxErrorException {

        List<VariableNode> nodes = new ArrayList<>();
        while(!tokens.isEmpty() && tokens.get(0).getTokenType() == Token.TokenType.IDENTIFIER){
         //nodes.add(getconstant());
         expectEndsOfLine();
      }
        return nodes;
  }
    private AssignmentNode Assignment() throws SyntaxErrorException {
    if(peek(1).getTokenType() == Token.TokenType.ASSIGNMENT && peek(0).getTokenType() == Token.TokenType.IDENTIFIER){
        String variableName = tokens.remove(0).getStringValue();
        matchAndRemove(Token.TokenType.ASSIGNMENT);
        Node expression = Expression();
        return new AssignmentNode(variableName, expression);
    }
        return null;
    }
    private VariableReferenceNode getVariableReference() throws SyntaxErrorException {
        String variableName = "";
        VariableReferenceNode variableRef = new VariableReferenceNode(variableName);
        while(tokens.get(0).getTokenType() == Token.TokenType.IDENTIFIER) {
            variableRef = new VariableReferenceNode(tokens.remove(0).getStringValue());
        }
        while(tokens.get(0).getTokenType()==Token.TokenType.NUMBER) {
            variableRef = new VariableReferenceNode(tokens.remove(0).getStringValue());
        }
        expectEndsOfLine();
        return variableRef;
    }

    public ArrayList<StatementNode> statements() throws SyntaxErrorException {
        ArrayList<StatementNode> statements = new ArrayList<>();
        expectEndsOfLine();
        matchAndRemove(Token.TokenType.INDENT);
        expectEndsOfLine();
        while(peek(0).getTokenType() != Token.TokenType.DEDENT) {
            statements.add(statement());
            expectEndsOfLine();
        }
        matchAndRemove(Token.TokenType.DEDENT);
        if(!tokens.isEmpty())
            expectEndsOfLine();
        return statements;

    }
    public ArrayList<FunctionNode> functionDefentions() throws SyntaxErrorException {
        ArrayList<FunctionNode> functions = new ArrayList<>();
        while(peek(0).getTokenType() == Token.TokenType.DEFINE) {
            Token name = matchAndRemove(Token.TokenType.IDENTIFIER);
            String functionName = "";
            if(name != null) {
                functionName = name.getStringValue();
            }
            matchAndRemove(Token.TokenType.LEFTPAREN);
            ArrayList<VariableNode> parameters = (ArrayList<VariableNode>) parameterDeclarations();
            matchAndRemove(Token.TokenType.RIGHTPAREN);
            expectEndsOfLine();

        }
        return functions;
    }
    public StatementNode statement() throws SyntaxErrorException {
        AssignmentNode assignment = Assignment();
        if(assignment != null) {
           return assignment;
        }
        WhileNode whileNode = parseWhile();
        if(whileNode != null) {
            return whileNode;
        }
        ForNode forNode = parseFor();
        if(forNode != null) {
            return forNode;
        }
        IfNode ifNode = parseIf();
        if(ifNode != null) {
            return ifNode;
        }
        StatementNode FunctionCallNode = parseFunctionCall();
        if(FunctionCallNode != null) {
            return FunctionCallNode;
        }
        return parseIf();
    }
    public ForNode parseFor() throws SyntaxErrorException {
        if(matchAndRemove(Token.TokenType.FOR)!=null){
            String name = matchAndRemove(Token.TokenType.IDENTIFIER).getStringValue();
            matchAndRemove(Token.TokenType.FROM);
            Node start = Expression();
            matchAndRemove(Token.TokenType.TO);
            Node end = Expression();
            matchAndRemove(Token.TokenType.DO);
            ArrayList<StatementNode> statements = statements();
            return new ForNode(new VariableReferenceNode(name), start, end, statements);
        }
        return null;
    }
    public WhileNode parseWhile() throws SyntaxErrorException {
        if(matchAndRemove(Token.TokenType.WHILE)!=null){
            expectEndsOfLine();
            BooleanCompareNode condition = booleanCompare();
            expectEndsOfLine();
            matchAndRemove(Token.TokenType.BEGIN);
            ArrayList<StatementNode> statements = statements();
            return new WhileNode(condition, statements);
        }
        return null;
    }
    public  RepeatNode repeat() throws SyntaxErrorException {
        if(matchAndRemove(Token.TokenType.REPEAT)!=null){
            expectEndsOfLine();
            matchAndRemove(Token.TokenType.BEGIN);
            ArrayList<StatementNode> statements = statements();
            matchAndRemove(Token.TokenType.UNTIL);
            BooleanCompareNode condition = booleanCompare();
            return new RepeatNode(condition, statements);
        }
        return null;
    }
    public IfNode parseIf() throws SyntaxErrorException {
        if( matchAndRemove(Token.TokenType.IF)!=null){
            expectEndsOfLine();
            BooleanCompareNode condition = booleanCompare();
            expectEndsOfLine();
            matchAndRemove(Token.TokenType.THEN);
            ArrayList<StatementNode> statements = statements();

            //first if node with first condition and statement
            IfNode ifNode = new IfNode(condition, statements);
            expectEndsOfLine();

            while (matchAndRemove(Token.TokenType.ELSEIF)!=null){
                expectEndsOfLine();
                condition = booleanCompare();
                expectEndsOfLine();
                matchAndRemove(Token.TokenType.THEN);
                statements = statements();
                ifNode.setElseStatements(statements);
                expectEndsOfLine();
            }
            if(matchAndRemove(Token.TokenType.ELSE)!=null){
                expectEndsOfLine();
                statements = statements();
                ifNode.setElseStatements(statements);
            }
            return ifNode;
        }
        return null;
    }
    public FunctionCallNode parseFunctionCall(){
        String name = "";
        ArrayList<ParameterNode> parameters = new ArrayList<>();
        Boolean isPrint = false;
        FunctionCallNode functionCall = new FunctionCallNode(name,parameters,isPrint);
        if(peek(0).getTokenType() == Token.TokenType.IDENTIFIER){
           name = String.valueOf(matchAndRemove(Token.TokenType.IDENTIFIER));
        }
        if(peek(0).getTokenType() == Token.TokenType.READ){
            name = String.valueOf(matchAndRemove(Token.TokenType.READ));
        }
        if(peek(0).getTokenType() == Token.TokenType.WRITE){
            name = String.valueOf(matchAndRemove(Token.TokenType.WRITE));
        }
        if(peek(0).getTokenType() == Token.TokenType.SQUAREROOT){
            name = String.valueOf(matchAndRemove(Token.TokenType.SQUAREROOT));
        }
        if(peek(0).getTokenType() == Token.TokenType.REALTOINTEGER){
            name = String.valueOf(matchAndRemove(Token.TokenType.REALTOINTEGER));
        }
        if(peek(0).getTokenType() == Token.TokenType.INTEGERTOREAL){
            name = String.valueOf(matchAndRemove(Token.TokenType.INTEGERTOREAL));
        }
        return functionCall;

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

}
