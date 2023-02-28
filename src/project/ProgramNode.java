package project;

import java.util.HashMap;
import java.util.Map;

public class ProgramNode extends Node {
    private Map<String, FunctionNode> functions;

    public ProgramNode() {
        this.functions = new HashMap<>();
    }

    public void addFunction(FunctionNode function) {
        this.functions.put(function.getFunctionName(), function);
    }

    public FunctionNode getFunction(String functionName) {
        return this.functions.get(functionName);
    }

    public boolean hasFunction(String functionName) {
        return this.functions.containsKey(functionName);
    }
}
