package project;

public class CharacterDataType extends InterpreterDataType{
    private char value;

    public CharacterDataType(char value){
        this.value = value;
    }
    public String toString(){
        return Character.toString(value);
    }
    public void fromString(String input){
        this.value = input.charAt(0);
    }
    public char getValue(){
        return value;
    }
    public void setValue(char value){
        this.value = value;
    }
}
