package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controller.GameStoreGUI;
import routes.Route;

import collection.queue.IQueue;

public class GameStore implements Serializable {

    public static final String SAVE_DATA = "data/IdentityGames.data";
    private List<Costumer> costumers;
    private List<Game> games;
    private Cashier[] cashiers;
    private List<Shelve> shelves;
    private IQueue<Costumer> line;

    public GameStore() {
        costumers = new ArrayList<>();
        games = new ArrayList<>();
        shelves = new ArrayList<>();
        dateRender();
    }

    public void initCashiers(int i) {
        cashiers = new Cashier[i];
    }

    // ---------------------------------------PERSISTENCE------------------------------------------------------

    public void dateRender() {
        File file = new File(SAVE_DATA);
        if (file.length() > 0) {
            loadInformation();
        }
    }

    public void saveInformation() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(SAVE_DATA));
            oos.writeObject(costumers);
            oos.writeObject(games);
            oos.writeObject(cashiers);
            oos.writeObject(shelves);
            oos.close();
        } catch (Exception e) {
            GameStoreGUI.getInstance().createAlert("The file data could not be founded", Route.ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadInformation() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(SAVE_DATA)));
            costumers = (ArrayList<Costumer>) ois.readObject();
            games = (ArrayList<Game>) ois.readObject();
            cashiers = (Cashier[]) ois.readObject();
            shelves = (ArrayList<Shelve>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            GameStoreGUI.getInstance().createAlert("The data could not be loaded", Route.ERROR);
        }

    }

    // --------------------------------------------------------------------------------------------------

    public List<Costumer> getCostumers() {
        return costumers;
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Shelve> getShelves() {
        return shelves;
    }

    public int getCashiers() {
        if(cashiers == null){
            return 0;
        }else{
            return cashiers.length;
        }
    }

    public void addClient(int id, String code, String name, ArrayList<Game> games) {
        Costumer newClient = new Costumer(id, code, name, games);
        costumers.add(newClient);
    }

    public void addGame(String name, String review, int price, int amount) {
        Game newGame = new Game(games.size() + 1, name, review, price, amount);
        games.add(newGame);
    }

    // -------------------------------SHELVES-----------------------------------------------------

    public boolean addGameToShelve(Game game, List<Game> aux) {
        boolean added = false;
        if (!games.isEmpty() && game != null) {
            for (int i = 0; i < games.size() && !added; i++) {
                if (games.get(i).getGameName().equals(game.getGameName()) && !aux.contains(games.get(i))) {
                    aux.add(games.get(i));
                    added = true;
                }
            }
        }
        return added;
    }

}
