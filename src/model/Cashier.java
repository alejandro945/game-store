package model;

import collection.stack.*;

public class Cashier {

    private Costumer currentCostumer;
    private boolean busy;
    private int toPay;
    private IStack<Game> pack;

    public Cashier() {

        this.busy = false;
        pack = new Stack<>();

    }

    public Costumer getCurrentCostumer() {
        return this.currentCostumer;
    }

    public boolean getBusy() {
        return this.busy;
    }

    public boolean isBusy() {
        return this.busy;
    }

    public int getToPay() {
        return this.toPay;
    }

    public IStack<Game> getPack() {
        return this.pack;
    }

}
