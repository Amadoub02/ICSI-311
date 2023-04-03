package project;

import java.util.ArrayList;

public abstract class BuiltInFunctionNode extends CallabaleNode{
    private boolean varadic;
    private ArrayList<VariableNode> arguments;
    private String name;

    public BuiltInFunctionNode(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
        this.name = name;
        this.arguments = arguments;
    }
    public abstract void execute(ArrayList<InterpreterDataType> arguments);

    public String getName(){
        return name;
    }
}
