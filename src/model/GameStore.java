package model;

import java.util.ArrayList;

public class GameStore {

    private ArrayList<Costumer> costumers;

    public GameStore() {
        costumers = new ArrayList<>();
    }

    public ArrayList<Costumer> getCostumers(){
        return costumers;
    }

    public void addClient(String code, String name, ArrayList<Game> games){
        Costumer newClient = new Costumer(code, name, games);
        costumers.add(newClient);
    }
}
