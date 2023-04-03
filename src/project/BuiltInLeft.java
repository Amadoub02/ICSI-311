package project;

import java.util.ArrayList;

public class BuiltInLeft extends BuiltInFunctionNode{
    public BuiltInLeft(String name, ArrayList<VariableNode> arguments, boolean varadic){
        super(name,arguments);
    }


    public void execute(ArrayList<InterpreterDataType> arguments) {
        int start = Integer.parseInt(arguments.get(1).toString());
        arguments.get(2).fromString(arguments.get(2).toString().substring(start));
    }
}
