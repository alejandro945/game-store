package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Costumer implements Serializable{
    private int id;
    private String code;
    private String name;
    private ArrayList<Game> wishList;
    private String sapa;

    public Costumer(int id, String code, String name, ArrayList<Game> wishList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.wishList = wishList;
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

    public String getSapa() {
       String msg = "";
        for (Game game : wishList) {
            msg += game.getCode() + " ";
        }
        return msg;
    }

}
