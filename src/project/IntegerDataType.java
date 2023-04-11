package project;

public class IntegerDataType extends InterpreterDataType {
    private int value;

    public IntegerDataType(String value){
        this.value = Integer.parseInt(value);
    }
    public IntegerDataType(int value){
        this.value = value;
    }
    public String toString(){
        return Integer.toString(value);
    }
    public void fromString(String input){
        this.value = Integer.parseInt(input);
    }
    public int getValue(){
        return value;
    }
}
