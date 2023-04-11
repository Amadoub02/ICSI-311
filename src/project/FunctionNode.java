package project;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class FunctionNode extends CallabaleNode {
    private String functionName;
    private List<VariableNode> functionParameters;
    private List<VariableNode> functionConstants;
    private List<VariableNode> functionVariables;
    private List<StatementNode> functionStatements;

    public FunctionNode(String functionName, List<VariableNode> functionParameters, List<VariableNode> functionConstants, List<VariableNode> functionVariables, List<StatementNode> functionStatements){
        super(functionName, (ArrayList<VariableNode>) functionParameters);
        this.functionName = functionName;
        this.functionParameters = functionParameters;
        this.functionConstants = functionConstants;
        this.functionVariables = functionVariables;
        this.functionStatements = functionStatements;
    }
    public FunctionNode(String value, ArrayList<VariableNode>params, ArrayList<VariableNode> vars, ArrayList<VariableNode> constants, ArrayList<StatementNode> body){
        super(value, vars);
        this.functionName = value;
        this.functionParameters = params;
        this.functionConstants = constants;
        this.functionVariables = vars;
    }
    public  ArrayList<StatementNode> getBody(){
        return (ArrayList<StatementNode>) functionStatements;
    }
    public void setBody(ArrayList<StatementNode> body){
        this.functionStatements = body;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    public String toString() {
        return "FunctionNode(" + this.functionName + ")";
    }

}

