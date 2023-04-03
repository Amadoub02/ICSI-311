package project;

import java.util.ArrayList;
import java.util.Scanner;

//represent read built in function
public class Read extends BuiltInFunctionNode{
    String name;
    ArrayList<FunctionNode> arguments;
    Scanner scanner;

    public Read(String name, ArrayList<VariableNode> arguments){
        super(name,arguments);
        scanner = new Scanner(System.in);
    }
    public void execute(ArrayList<InterpreterDataType> arguments){
        for(InterpreterDataType arg : arguments){
            System.out.println("Enter a value: ");
            arg.fromString(scanner.nextLine());
        }
        scanner.close();
    }
}
