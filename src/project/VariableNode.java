package project;

public class VariableNode extends Node{

    public  VariableNode(String variableName, String variableType){
        this.variableName = variableName;
        this.variableType = variableType;
    }
    private String variableName;
    private String variableType;

    public String toString() {
        return "VariableNode" + "(" + this.variableName + this.variableType +")";
    }
}
