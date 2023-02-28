package project;

public class RealNode extends Node {
    public RealNode(float numberValue) {
        this.numberValue = Float.parseFloat(String.valueOf(numberValue));
    }
    private float numberValue;
    public float getNumberValue() {
        return this.numberValue;
    }
    @Override
    public String toString() {
        return "RealNode" + "(" + this.numberValue + ")";
    }


    public Float getFloatValue() {
        return numberValue;
    }
}

