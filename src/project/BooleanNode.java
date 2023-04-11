package project;

public class BooleanNode extends Node{
    private boolean bool;

    public BooleanNode(boolean bool){
        this.bool = bool;
    }

    public String toString() {
        return "BooleanNode" + "(" + this.bool +")";
    }

    public boolean getValue() {
        return bool;
    }
}
