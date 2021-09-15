package model;

import java.io.Serializable;
import java.util.ArrayList;

import collection.stack.IStack;
import collection.stack.Stack;

public class Costumer implements Serializable{
    private int id;
    private String code;
    private String name;
    private ArrayList<Game> wishList;
    private IStack<Game> shopBasket;

    public Costumer(int id, String code, String name, ArrayList<Game> wishList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.wishList = wishList;
        shopBasket = new Stack<>();
    }

    public ArrayList<Game> getWishList() {
        return this.wishList;
    }

    public void setWishList(ArrayList<Game> wishList) {
        this.wishList = wishList;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGames() {
       String games = "";
       String separator = "";
       for(int i = 0; i<wishList.size(); i++){
        games += separator + wishList.get(i).getCode();
        separator = "-";
       }
        return games;
    }
}
