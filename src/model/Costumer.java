package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Costumer<T extends Comparable<T>> {
    private T code;
    private String name;
    private String  lastName;
    private long id;
    HashMap<String,Float> gameList = new HashMap<String,Float>();

    public Costumer(T code, String name, String lastName, Long id, ArrayList<Game> listWish){
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }

    public T getCode(){
        return code;
    }

    public void setCode(T code){
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
