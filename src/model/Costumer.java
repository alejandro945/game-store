package model;

import java.util.ArrayList;

public class Costumer {
    private String code;
    private String name;
    private ArrayList<Game> wishList;

    public Costumer(String code, String name, ArrayList<Game> listWish) {
        this.code = code;
        this.name = name;
        wishList = listWish;
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

    public ArrayList<Game> getWishList() {
        return this.wishList;
    }

    public void setWishList(ArrayList<Game> wishList) {
        this.wishList = wishList;
    }

}
