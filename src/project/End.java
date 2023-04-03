package project;

import java.util.ArrayList;

public class End extends BuiltInFunctionNode{
    public End(String name, ArrayList<VariableNode> arguments) {
        super(name, arguments);
    }

    @Override
    public void execute(ArrayList<InterpreterDataType> arguments) {
        System.exit(0);
    }

}
