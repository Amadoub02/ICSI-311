package project;

import java.util.ArrayList;

public class BuiltInSubtring extends BuiltInFunctionNode{
    public BuiltInSubtring(String functionName, ArrayList<VariableNode> arguments, boolean varadic) {
        super(functionName, arguments);
    }

    public void execute(ArrayList<InterpreterDataType> arguments) {
        int start = Integer.parseInt(arguments.get(1).toString());
        int end = Integer.parseInt(arguments.get(2).toString());
        arguments.get(3).fromString(arguments.get(3).toString().substring(start, end));
    }

}
