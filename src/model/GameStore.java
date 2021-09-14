package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controller.GameStoreGUI;
import routes.Route;

public class GameStore implements Serializable {

    public static final String SAVE_DATA = "data/IdentityGames.data";
    private List<Costumer> costumers;
    private List<Game> games;
    private int cashiers;

    public GameStore() {
        costumers = new ArrayList<>();
        games = new ArrayList<>();
        cashiers = 0;
        dateRender();
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
            ois.close();
        } catch (Exception e) {
            // GameStoreGUI.getInstance().createAlert("The data could not be loaded",
            // Route.ERROR);
            e.printStackTrace();
        }

    }

    // --------------------------------------------------------------------------------------------------

    public List<Costumer> getCostumers() {
        return costumers;
    }

    public List<Game> getGames() {
        return games;
    }

    public int getCashiers() {
        return cashiers;
    }

    public void setCashiers(int cashiers) {
        this.cashiers = cashiers;
    }

    public void addClient(int id, String code, String name, ArrayList<Game> games) {
        Costumer newClient = new Costumer(id, code, name, null);
        newClient.setWishList(games);
        costumers.add(newClient);
    }

    public void addGame(String name, String review, int price, int amount) {
        Game newGame = new Game(games.size() + 1, name, review, price, amount);
        games.add(newGame);
    }
}
