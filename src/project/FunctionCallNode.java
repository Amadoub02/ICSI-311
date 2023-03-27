package project;

import java.util.ArrayList;

public class FunctionCallNode extends StatementNode{
    private String functionName;
    private ArrayList<ParameterNode> parameters;
    private ArrayList<Node> parameterNodes;
    private Boolean isPrint;

    public FunctionCallNode(String functionName, ArrayList<ParameterNode> parameters, Boolean isPrint) {
        this.functionName = functionName;
        this.parameters = parameters;

        this.isPrint = isPrint;
    }

    public String getFunctionName() {
        return functionName;
    }
    public ArrayList<ParameterNode> getParameters() {
        return parameters;
    }
    public Boolean getIsPrint() {
        return isPrint;
    }
    public Boolean setIsPrint() {
        return isPrint;
    }
    public String toString() {
        return "FunctionCallNode: " + functionName + " parameters: " + parameters + " isPrint: " + isPrint + " ";
    }



}
