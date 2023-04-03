package project;

import java.util.ArrayList;

public class Start extends BuiltInFunctionNode {
    public Start(String name, ArrayList<VariableNode> arguments) {
        super(name, arguments);
    }

    @Override
    public void execute(ArrayList<InterpreterDataType> arguments) {
        for (InterpreterDataType arg : arguments) {
            System.out.println(arg.toString());
        }
    }
}

