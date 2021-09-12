package model;

import java.util.ArrayList;

public class Costumer {
    private int id;
    private String code;
    private String name;
    private ArrayList<Game> wishList;

    public Costumer(int id, String code, String name, ArrayList<Game> wishList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.wishList = new ArrayList<>();
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
        String getGames = "";
        for (Game game : wishList) {
            getGames += game.getCode() + " ";
        }
        System.out.println(getGames);
        return getGames;
    }

}
