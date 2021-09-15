package model;


import collection.hashTable.IHash;

public class Shelve {
    private IHash<Integer, Game> gameShelve;
    private String nameShelve;

    public Shelve(String nameShelve, IHash<Integer, Game> gameShelve) {
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
