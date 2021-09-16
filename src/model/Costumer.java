package model;

import java.io.Serializable;
import java.util.ArrayList;

import collection.stack.IStack;
import collection.stack.Stack;

public class Costumer implements Serializable{
    private int id;
    private String code;
    private String name;
    private int timeInShop;
    private ArrayList<Game> wishList;
    private IStack<Game> shopBasket;

    public Costumer(int id, String code, String name, ArrayList<Game> wishList) {
        this.id = id;
        this.code = code;
        this.name = name;
        timeInShop = 0;
        this.wishList = wishList;
        shopBasket = new Stack<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeInShop() {
        return timeInShop;
    }

    public void setTimeInShop(int timeInShop) {
        this.timeInShop = timeInShop;
    }

    public ArrayList<Game> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Game> wishList) {
        this.wishList = wishList;
    }

    public IStack<Game> getShopBasket() {
        return shopBasket;
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
