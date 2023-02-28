package project;

public class StringNode extends Node{
    private String string;

    public StringNode(String string){
        this.string = string;
    }

    public String toString() {
        return "StringNode" + "(" + this.string +")";
    }
}
