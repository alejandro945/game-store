package model;

import java.io.Serializable;

import collection.hashTable.Hash;
import collection.hashTable.IHash;

public class Shelve implements Serializable {
    private IHash<Integer, Game> gameShelve;
    private String nameShelve;
    private int size;

    public Shelve(String nameShelve, int sizeShelves) {
        this.nameShelve = nameShelve;
        size = sizeShelves;
        gameShelve = new Hash<>(sizeShelves);
    }

    public String getNameShelve() {
        return this.nameShelve;
    }

    public int getSize() {
        return this.size;
    }

    public IHash<Integer,Game> getGameShelve() {
        return this.gameShelve;
    }

}
