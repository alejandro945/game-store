package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controller.GameStoreGUI;
import routes.Route;

public class GameStore {

    public static final String SAVE_DATA = "data/IdentityGames.report";
    private List<Costumer> costumers;
    private List<Game> games;
    private int cashiers;

    public GameStore() {
        costumers = new ArrayList<>();
        games = new ArrayList<>();
        cashiers = 0;
    }

    // ---------------------------------------PERSISTENCE------------------------------------------------------

    public File getDataFile() {
        File file = new File(SAVE_DATA);
        return file;
    }

    public void saveInformation() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream oos = null;
        File file = new File(SAVE_DATA);
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(costumers);
            oos.writeObject(games);
            oos.close();
        } catch (FileNotFoundException e) {
            GameStoreGUI.getInstance().createAlert("The file data could not be founded", Route.ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadInformation() throws IOException, ClassNotFoundException {
        if (getDataFile().length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(SAVE_DATA)));
            costumers = (ArrayList<Costumer>) ois.readObject();
            games = (ArrayList<Game>) ois.readObject();

            ois.close();
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
        Costumer newClient = new Costumer(id, code, name, games);
        costumers.add(newClient);
    }

    public void addGame(String name, String review, int price, int amount) {
        Game newGame = new Game(games.size() + 1, name, review, price, amount);
        games.add(newGame);
    }
}
