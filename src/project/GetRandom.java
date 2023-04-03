package project;

import java.util.ArrayList;

public class GetRandom extends BuiltInFunctionNode{
    private Float value;
    public GetRandom(String name, ArrayList<VariableNode> arguments, boolean varadic){
        super(name,arguments);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(InterpreterDataType arg : arguments){
            value = (float) Math.random() * Float.parseFloat(arg.toString());
        }
    }
    public String toString(){
        return Integer.toString((int) Math.random() * Integer.parseInt(value.toString()));
    }
}

