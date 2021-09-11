package model;

import java.util.Hashtable;

public class Shelve {

    private Hashtable<Integer, Game> gameShelve;
    private String nameShelve;

    public Shelve(Hashtable<Integer, Game> gameShelve, String nameShelve) {
        this.gameShelve = gameShelve;
        this.nameShelve = nameShelve;
    }

    public String getNameShelve() {
        return this.nameShelve;
    }

    public void setNameShelve(String nameShelve) {
        this.nameShelve = nameShelve;
    }

}
