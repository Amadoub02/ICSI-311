package project;

import java.util.ArrayList;

public class BuiltInRight extends BuiltInFunctionNode{
    public BuiltInRight(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        int start = Integer.parseInt(arguments.get(1).toString());
        arguments.get(2).fromString(arguments.get(2).toString().substring(start));
    }
}
