package project;

public class ParameterNode {
    private boolean isVariable = false;
    private String variableName;
    private Token.TokenType type;
    private String value;

    public ParameterNode(String variableName, Token.TokenType type, String value, boolean isVariable) {
        this.variableName = variableName;
        this.type = type;
        this.value = value;
        this.isVariable = isVariable;
    }
    public boolean isVariable() {
        return isVariable;
    }
    public String getVariableName() {
        return variableName;
    }
    public Token.TokenType getType() {
        return type;
    }
    public String getValue() {
        return value;
    }
    public String setValue(String value) {
        return this.value = value;
    }
    public String toString() {
        return "ParameterNode: " + variableName + " type: " + type + " value: " + value + " isVariable: " + isVariable + " ";
    }

}
