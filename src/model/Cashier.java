package model;

import java.io.Serializable;

import collection.stack.*;

public class Cashier implements Serializable {

    private Costumer currentCostumer;
    private boolean busy;
    private int toPay;
    private IStack<Game> pack;

    public Cashier() {
        currentCostumer = null;
        busy = false;
        toPay = 0;
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
