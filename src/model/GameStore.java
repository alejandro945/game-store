package model;

import java.util.ArrayList;
import java.util.List;

public class GameStore {

    private List<Costumer> costumers;
    private List<Game> games;

    public GameStore() {
        costumers = new ArrayList<>();
        games = new ArrayList<>();
    }

    public List<Costumer> getCostumers(){
        return costumers;
    }

    public List<Game> getGames(){
        return games;
    }

    public void addClient(String code, String name, ArrayList<Game> games){
        Costumer newClient = new Costumer(code, name, games);
        costumers.add(newClient);
    }
}
