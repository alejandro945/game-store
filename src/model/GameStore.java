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

    public List<Costumer> getCostumers() {
        return costumers;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addClient(String code, String name, ArrayList<Game> games) {
        Costumer newClient = new Costumer(code, name, games);
        costumers.add(newClient);
    }

    public void addGames(String name, String review, int price, int amount) {
        Game newGame = new Game(games.size() + 1, name, review, price, amount);
        games.add(newGame);
    }
}
