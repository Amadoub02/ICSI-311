package project;

public class FloatNode extends Node {
    public FloatNode(float numberValue) {
        this.numberValue = Float.parseFloat(String.valueOf(numberValue));
    }
    private float numberValue;
    public float getNumberValue() {
        return this.numberValue;
    }
    @Override
    public String toString() {
        return "FloatNode" + "(" + this.numberValue + ")";
    }


    public Float getFloatValue() {
        return numberValue;
    }
}

