package project;

import java.util.ArrayList;

//represent write built in function
public class Write extends BuiltInFunctionNode{

public Write(String name, ArrayList<VariableNode> arguments, boolean varadic){
        super(name,arguments);
    }

    // Loop through all the arguments passed to the 'Write' function
    // method to execute  write function loops over arg and prints it out
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(int i = 0; i < arguments.size(); i++){
            System.out.print(arguments.get(i).toString());
        }
    }
}
