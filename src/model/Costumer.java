package model;

import java.util.ArrayList;

public class Costumer {
    private int id;
    private String code;
    private String name;
    private ArrayList<Game> wishList;
    private String message;

    public Costumer(int id, String code, String name, ArrayList<Game> listWish) {
        this.id = id;
        this.code = code;
        this.name = name;
        wishList = listWish;
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

    public String getMessage() {
        for (Game game : wishList) {
            message += game.getCode() + " ";
        }
        System.out.println(message);
        return message;
    }

    public void setWishList(ArrayList<Game> wishList) {
        this.wishList = wishList;
    }

}
