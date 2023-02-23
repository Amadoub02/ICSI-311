package project;

public class IntegerNode extends Node{
    public IntegerNode(int numberValue) {
        this.numberValue = Integer.parseInt(String.valueOf(numberValue));
    }
    private int numberValue;
    @Override
    public String toString() {
        return "IntegerNode" + "(" + this.numberValue + ")";
    }

    public Float getIntegerValue() {
        return (float) numberValue;
    }
}
