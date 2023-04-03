package project;

import java.util.ArrayList;

public class SquareRoot extends BuiltInFunctionNode{
    private Float value;

    public SquareRoot(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(InterpreterDataType arg : arguments){
            value = (float) Math.sqrt(Float.parseFloat(arg.toString()));
        }
    }
    public String toString(){
        return Float.toString(value);
    }
}
