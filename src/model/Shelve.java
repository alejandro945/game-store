package model;

import java.util.Hashtable;

public class Shelve {
    private Hashtable<Integer, Game> gameShelve;
    private String nameShelve;

    public Shelve(String nameShelve, Hashtable<Integer, Game> gameShelve) {
        this.nameShelve = nameShelve;
        this.gameShelve = gameShelve;
    }

    public String getNameShelve() {
        return this.nameShelve;
    }

    public void setNameShelve(String nameShelve) {
        this.nameShelve = nameShelve;
    }

}
