package project;

public class ArrayDataType extends InterpreterDataType{
    InterpreterDataType[] data;

    public ArrayDataType(InterpreterDataType[] data){
        this.data = data;
    }
    public String toString(){
        return data.toString();
    }

    @Override
    public void fromString(String input) {

    }

}
