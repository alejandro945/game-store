package model;

import java.io.Serializable;

import collection.stack.*;

public class Cashier implements Serializable {
    private int id;
    private Costumer currentCostumer;
    private boolean busy;
    private int toPay;
    private IStack<Game> pack;

    public Cashier(int id) {
        this.id = id;
        currentCostumer = null;
        busy = false;
        toPay = 0;
        pack = new Stack<>();
    }

    public int getId() {
        return id;
    }

    public Costumer getCurrentCostumer() {
        return currentCostumer;
    }

    public boolean isBusy() {
        return busy;
    }

    public synchronized int getToPay() {
        return toPay;
    }

    public IStack<Game> getPack() {
        return pack;
    }

    public void setToPay(int price) {
        toPay += price;
    }

    public void setPayment(int price) {
        toPay = price;
    }

    public void newPack(){
        pack = new Stack<>();
    }

    public void setBusy(boolean state) {
        busy = state;
    }

}
