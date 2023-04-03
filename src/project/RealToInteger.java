package project;

import java.util.ArrayList;

public class RealToInteger extends BuiltInFunctionNode{
    private int value;

    public RealToInteger(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(InterpreterDataType arg : arguments){
            value = (int) Float.parseFloat(arg.toString());
        }
    }
    public String toString(){
        return Integer.toString(value);
    }
}
