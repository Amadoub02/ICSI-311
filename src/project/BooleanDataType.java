package project;

public class BooleanDataType extends InterpreterDataType{
    private boolean value;

    public BooleanDataType(boolean value){
        this.value = value;
    }
    public BooleanDataType(){
        this.value = false;
    }
    public String toString(){
        return Boolean.toString(value);
    }
    @Override
    public void fromString(String input) {
        this.value = Boolean.parseBoolean(input);
    }

    public boolean getValue(){
        return value;
    }
    public void setValue(boolean value){
        this.value = value;
    }
}
