package project;

public class RealDataType {
    private Float value;

    public RealDataType(String value){
        this.value = Float.parseFloat(value);
    }
    public RealDataType(float value){
        this.value = value;
    }
    public String toString(){
        return Float.toString(value);
    }
    public void fromString(String input){
        this.value = Float.parseFloat(input);
    }
    public float getValue(){
        return value;
    }

}
