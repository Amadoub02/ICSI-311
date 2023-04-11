package project;

import java.lang.reflect.Type;

public class VariableNode extends Node{
    private boolean isConstant;
    private String name;
    private Node Value;
    private Type type;

    public boolean isConstant() {
        return isConstant;
    }
    public String getName() {
        return name;
    }
    public Node getValue() {
        return Value;
    }
    public Type getType() {
        return type;
    }
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
