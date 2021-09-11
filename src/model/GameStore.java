package model;

import java.util.ArrayList;

public class GameStore {

    private static GameStore instance;

    private ArrayList<Costumer<String>> costumers;
    private ArrayList<Game> listWish;

    private GameStore() {
        costumers = new ArrayList<>();
        listWish = new ArrayList<>();
    }

    public void initListWish(){
        listWish = new ArrayList<>();
    }

    public ArrayList<Game> getListWish(){
        return listWish;
    }

    public static GameStore getInstance() {
        if (instance == null) {
            instance = new GameStore();
        }
        return instance;
    }
}
