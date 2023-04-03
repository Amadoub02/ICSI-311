package project;

import java.util.ArrayList;

public class IntegerToReal extends BuiltInFunctionNode{
    private Float value;
    public IntegerToReal(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
    }
    public String toString(String input){
        return Float.toString(Float.parseFloat(input));
    }
    public void fromString(String input){
        this.value = Float.parseFloat(input);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(InterpreterDataType arg : arguments){
            value = Float.parseFloat(arg.toString());
        }
    }

}
