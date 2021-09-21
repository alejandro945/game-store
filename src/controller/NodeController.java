package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Cashier;
import model.Costumer;
import model.Game;
import routes.Route;

public class NodeController {
    @FXML
    private Label costumerName;

    @FXML
    private Label costumerId;

    @FXML
    private Label costumerTime;

    @FXML
    private JFXTextArea costumerCode;

    @FXML
    private Label slot;

    @FXML
    private Label game;

    @FXML
    private Pane currentCostumer;

    @FXML
    private Label busy;

    @FXML
    private Label active;

    @FXML
    private Label toPay;

    @FXML
    private Label cashier;

    @FXML
    private Label pack;
    private Cashier c;

    public NodeController() {

    }

    public void getCostumer(Costumer c) {
        costumerCode.setText(c.getGames());
        costumerId.setText(String.valueOf(c.getId()));
        costumerName.setText(c.getName());
        costumerTime.setText(c.getTimeInShop() + " MIN");
    }

    public void getCostumerWithBasket(Costumer c) {
        costumerCode.setText(c.getShopBasket().getInfo());
        costumerId.setText(String.valueOf(c.getId()));
        costumerName.setText(c.getName());
        costumerTime.setText(c.getTimeInShop() + " MIN");
    }

    public void getRack(String s, Game g) {
        slot.setText(s);
        game.setText("Cod. " + g.getCode() + "  $ " + g.getPrice());
    }

    public void setCashier(Cashier c) {
        cashier.setText("Cashier: " + c.getId());
        if (!c.isBusy()) {
            active.toFront();
        } else {
            busy.toFront();
        }
        this.c = c;
    }

    public synchronized Cashier getCashier() {
        return c;
    }

    public synchronized void setPack(String p) {
        pack.setText(p);
    }

    public synchronized void  setToPay(int amount) {
        toPay.setText("$ " + amount);
    }

    public void setCurrent(Costumer c) {
        if(c!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
            fxmlLoader.setController(this);
            try {
                Pane pane = fxmlLoader.load();
                getCostumerWithBasket(c);
                currentCostumer.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            currentCostumer.getChildren().clear();
        }
    }
}
