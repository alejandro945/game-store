package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import collection.queue.Queue;
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

    public void initLine() {
        line = new Queue<>();
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
            e.printStackTrace();
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

    // -----------------------------------------GETTERS----------------------------------------------

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
        if (cashiers == null) {
            return 0;
        } else {
            return cashiers.length;
        }
    }

    public IQueue<Costumer> getLine() {
        return line;
    }

    // -------------------------------ADDING-----------------------------------------------------

    public boolean addClient(int id, String code, String name, ArrayList<Game> games) {
        boolean added = false;
        Costumer newClient = new Costumer(id, code, name, games);
        if (!verifyRepeatCostumer(name)) {
            costumers.add(newClient);
            added = true;
        }
        return added;
    }

    public String addShelve(String name, int size) {
        String msg = "Shelve already in app";
        Shelve newShelve = new Shelve(name, size);
        if (!verifyRepeatShelve(name)) {
            shelves.add(newShelve);
            msg = "Shelve added succesfully";
        }
        return msg;
    }

    public String addGame(String name, String review, int price, String shelveName, int amount) {
        String msg = "We dont have more spaces in the shelve selected";
        Shelve aux = searchShelve(shelveName);
        if (!aux.getGameShelve().isFull()) {
            Game newGame = new Game(games.size() + 1, name, review, price, shelveName, amount);
            games.add(newGame);
            aux.getGameShelve().addElement(newGame.getCode(), newGame);
            msg = "Game added succesfully";
        }
        return msg;
    }

    public void removeGame(Game g) {
        searchShelve(g.getShelveName()).getGameShelve().remove(g.getCode());
        games.remove(g);
    }

    public boolean removeShelve(Shelve s) {
        boolean removed = false;
        if (s.getGameShelve().isEmpty()) {
            removed = true;
            shelves.remove(s);
        }
        return removed;
    }

    // -------------------------------VALIDATIONS-----------------------------------------------------

    public boolean verifyRepeatShelve(String name) {
        boolean repeated = false;
        for (int i = 0; i < shelves.size() && !repeated; i++) {
            if (shelves.get(i).getNameShelve().equals(name)) {
                repeated = true;
            }
        }
        return repeated;
    }

    public boolean addCostumerToLine(Costumer newCostumer) {
        boolean added = false;
        if (!line.search(newCostumer)) {
            line.enqueue(newCostumer);
            newCostumer.setTime(line.size());
            added = true;
        }
        return added;
    }

    public boolean verifyRepeatCostumer(String name) {
        boolean repeated = false;
        for (int i = 0; i < costumers.size() && !repeated; i++) {
            if (costumers.get(i).getName().equals(name)) {
                repeated = true;
            }
        }
        return repeated;
    }

    public Costumer searchCostumer(String name) {
        Costumer c = null;
        for (Costumer cos : costumers) {
            if (cos.getName().equals(name)) {
                c = cos;
            }
        }
        return c;
    }

    public Shelve searchShelve(String name) {
        Shelve s = null;
        for (Shelve sh : shelves) {
            if (sh.getNameShelve().equals(name)) {
                s = sh;
            }
        }
        return s;
    }
    // -------------------------------SORTING-----------------------------------------------------

    public void bubbleSort(Costumer c){
        ArrayList<Game> list = c.getWishList();
        for (int i = 0; i<list.size(); i++){
            for (int j = 1; j<list.size() - i; j++){
                Shelve s1 = searchShelve(list.get(j-1).getShelveName());
                Shelve s2 = searchShelve(list.get(j).getShelveName());
                if(s1.getNameShelve().compareTo(s2.getNameShelve())  == 0){
                    if(s1.getGameShelve().getIndexInTable(list.get(j-1).getCode()) > s1.getGameShelve().getIndexInTable(list.get(j).getCode())){
                        Game temp = list.get(j-1);
                        list.set(j-1, list.get(j));
                        list.set(j, temp);
                    }
                } else if (s1.getNameShelve().compareTo(s2.getNameShelve()) > 0) {
                    Game temp = list.get(j-1);
                    list.set(j-1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        c.setWishList(list);
    }

    public void insertionSort(Costumer c){
        ArrayList<Game> list = c.getWishList();
        for (int i = 0; i< list.size()-1; i++){
            Game minor = list.get(i);
            int cual = i;
            for (int j = i+1; j<list.size(); j++){
                Shelve s1 = searchShelve(list.get(i).getShelveName());
                Shelve s2 = searchShelve(list.get(j).getShelveName());
                if(s1.getNameShelve().compareTo(s2.getNameShelve())  == 0){
                    if(s1.getGameShelve().getIndexInTable(list.get(j).getCode()) < s1.getGameShelve().getIndexInTable(list.get(i).getCode())){
                        minor = list.get(j);
                        cual = j;
                    }
                } else if (s1.getNameShelve().compareTo(s2.getNameShelve()) > 0){
                    Game temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
            Game temp = list.get(i);
            list.set(i, minor);
            list.set(cual, temp);
        }
        c.setWishList(list);
    }
}
