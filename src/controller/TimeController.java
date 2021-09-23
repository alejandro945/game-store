package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import collection.stack.IStack;
import collection.stack.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Costumer;
import model.Game;
import model.GameStore;
import routes.Route;

import java.io.IOException;
import java.util.List;

public class TimeController {

    @FXML
    private Pane pInformationClient;

    @FXML
    private JFXButton btnUpdateTime;

    @FXML
    private JFXTextField txtNewTime;

    @FXML
    private Button next;

    @FXML
    private AnchorPane lineR;

    @FXML
    private HBox line;

    private int clientOrder = 0;

    @FXML
    public void onUpdateTime(ActionEvent event) throws IOException {
        convertToStackGame(getArray().get(clientOrder));
        clientOrder++;
        if (clientOrder < getArray().size()) {
            addCostumer(getArray().get(clientOrder), 1);
            txtNewTime.setText(setCostumerTime(getArray().get(clientOrder)) + " MIN");
        } else {
            orderClient();
            initLine();
            btnUpdateTime.setDisable(true);
            next.setDisable(false);
        }

    }

    public void initTimeUpdate() throws IOException {
        clientOrder = 0;
        addCostumer(getArray().get(clientOrder), 1);
        txtNewTime.setText(setCostumerTime(getArray().get(0)) + " MIN");
        next.setDisable(true);
    }

    private void addCostumer(Costumer c, int type) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        if (type == 1) {
            controller.getCostumer(c);
            pInformationClient.getChildren().add(pane);
            pInformationClient.toBack();
        } else {
            controller.getCostumerWithBasket(c);
            line.getChildren().add(pane);
            line.toBack();
        }
    }

    public List<Costumer> getArray() {
        List<Costumer> getClientstoQueue = GameStoreGUI.getInstance().getGameStore().getLine().convertQueueToArr();
        return getClientstoQueue;
    }

    public int setCostumerTime(Costumer c) {
        for (int i = 0; i < c.getWishList().size(); i++) {
            c.setTimeInShop();
        }
        return c.getTimeInShop();
    }

    public void convertToStackGame(Costumer c) {
        IStack<Game> s = new Stack<>();
        for (Game g : c.getWishList()) {
            if(g.validateInventory()){
                s.push(g);
            }else{
                GameStoreGUI.getInstance().createAlert("Sorry, " + g.getGameName() + " out of stock", Route.ERROR);
            }
        }
        c.setShopBasket(s);
    }

    public void orderClient() {
        List<Costumer> aux = getArray();
        for (int i = 1; i < aux.size(); i++) {
            for (int j = i; j > 0 && aux.get(j - 1).getTimeInShop() > aux.get(j).getTimeInShop(); j--) {
                Costumer temp = aux.get(j);
                aux.set(j, aux.get(j - 1));
                aux.set(j - 1, temp);
            }
        }
        GameStoreGUI.getInstance().getGameStore().getLine().clear();
        for (int i = 0; i < aux.size(); i++) {
            GameStoreGUI.getInstance().getGameStore().getLine().enqueue(aux.get(i));
        }
    }

    private void initLine() throws IOException {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        line.getChildren().clear();
        line.setPadding(new Insets(10));
        line.setSpacing(12);
        int count = 0;
        for (Costumer c : g.getLine().convertQueueToArr()) {
            addCostumer(c, 2);
            count++;
        }
        lineR.setMinWidth(lineR.getWidth() + (170 * count));
    }

    @FXML
    public void onNext(ActionEvent event) throws IOException {
        GameStoreGUI.getInstance().renderScreen(Route.SECTION4);
        GameStoreGUI.getInstance().showPayment();
    }
}
