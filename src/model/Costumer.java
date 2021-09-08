package model;

import java.util.HashMap;

public class Costumer<T extends Comparable<T>> {
    private T code;
    HashMap<String,Float> gameList = new HashMap<String,Float>();

    public Costumer(T code){
        this.code = code;
    }

    public T getCode(){
        return code;
    }

    public void setCode(T code){
        this.code = code;
    }
}
