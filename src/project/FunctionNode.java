package project;

import java.util.List;

public class FunctionNode extends Node {
    private String functionName;
    private List<VariableNode> functionParameters;
    private List<VariableNode> functionConstants;
    private List<VariableNode> functionVariables;
    private List<StatementNode> functionStatements;

    public FunctionNode(String functionName, List<VariableNode> functionParameters, List<VariableNode> functionConstants, List<VariableNode> functionVariables, List<StatementNode> functionStatements){
        this.functionName = functionName;
        this.functionParameters = functionParameters;
        this.functionConstants = functionConstants;
        this.functionVariables = functionVariables;
        this.functionStatements = functionStatements;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    public String toString() {
        return "FunctionNode(" + this.functionName + ")";
    }

}

