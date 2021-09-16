package model;

import java.io.Serializable;

import collection.hashTable.IHash;

public class Shelve implements Serializable {
    private IHash<Integer, Game> gameShelve;
    private String nameShelve;
    private int amountShelves;

    public Shelve(String nameShelve, int amountShelves, IHash<Integer, Game> gameShelve) {
        this.nameShelve = nameShelve;
        this.gameShelve = gameShelve;
        amountShelves = 0;
    }

    public String getNameShelve() {
        return this.nameShelve;
    }

    public void setNameShelve(String nameShelve) {
        this.nameShelve = nameShelve;
    }

    public int getAmountShelves() {
        return this.amountShelves;
    }

    public void setAmountShelves(int amountShelves) {
        this.amountShelves = amountShelves;
    }

}
