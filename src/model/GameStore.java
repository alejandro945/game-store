package model;

import java.util.ArrayList;

public class GameStore {

    private static GameStore instance;

    private ArrayList<Costumer<String>> costumers;

    private GameStore() {
        costumers = new ArrayList<>();
    }

    public ArrayList<Costumer<String>> getCostumers(){
        return costumers;
    }

    public static GameStore getInstance() {
        if (instance == null) {
            instance = new GameStore();
        }
        return instance;
    }

    public void addClient(String code, String name, String lastName, Long id, ArrayList<Game> games){
        Costumer<String> newClient = new Costumer<>(code, name, lastName, id, games);
    }
}
