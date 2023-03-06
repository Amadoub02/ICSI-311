package project;

public class VariableNode extends Node{

    public  VariableNode(varType variableName, String variableType){
        this.variableName = String.valueOf(variableName);
        this.variableType = variableType;
    }
    public enum varType {
        INTEGER,
        REAL
    }
    public String variableName;
    public String variableType;

    public String toString() {
        return "VariableNode" + "(" + this.variableName + this.variableType +")";
    }
}
