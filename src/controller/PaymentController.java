package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Cashier;
import model.Costumer;
import model.GameStore;
import routes.Route;
import thread.PaymentThread;

public class PaymentController {

    @FXML
    private AnchorPane lineR;

    @FXML
    private HBox line;

    @FXML
    private AnchorPane lineR1;

    @FXML
    private HBox cashiers;

    public void initPayment() throws IOException {
        initLine();
        initCashiers();
    }

    public void initLine() throws IOException {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        line.getChildren().clear();
        line.setPadding(new Insets(10));
        line.setSpacing(12);
        int count = 0;
        for (Costumer c : g.getLine().convertQueueToArr()) {
            addCostumer(c);
            count++;
        }
        lineR.setMinWidth(lineR.getWidth() + (80 * count));
    }

    private void initCashiers() throws IOException {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        cashiers.getChildren().clear();
        cashiers.setPadding(new Insets(10));
        cashiers.setSpacing(12);
        int count = 0;
        for (int i = 0; i < g.getLengthCashiers(); i++) {
            addCashier(g.getCashiers()[i]);
            count++;
        }
        lineR1.setMinWidth(lineR1.getWidth() + (90 * count));
    }

    private void addCostumer(Costumer c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getCostumer(c);
        line.getChildren().add(pane);
        line.toBack();
    }

    private void addCashier(Cashier c) throws IOException {
        c.newPack();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_CASHIER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.setCashier(c);
        cashiers.getChildren().add(pane);
        cashiers.toBack();
        PaymentThread p = new PaymentThread(GameStoreGUI.getInstance().getGameStore().getLine(), controller,this);
        p.start();
    }


}
