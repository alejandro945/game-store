package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private Button next;

    @FXML
    private Label report;

    private String repor = "";

    public synchronized String getReport() {
        return repor;
    }

    public synchronized void setReport(String rp) {
        repor += rp;
    }

    public void initCongratulations() {
        report.setText(repor);
    }

    public void initBtn(){
        boolean render = true;
        Cashier[] c = GameStoreGUI.getInstance().getGameStore().getCashiers();
        for (int i = 0; i < c.length; i++) {
            if(c[i].isBusy()){
                render=false;
            }
        }
        if(render){
            next.setDisable(false);
        }
    }

    @FXML
    public void end(ActionEvent event) {
        repor = "";
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    public void initPayment() throws IOException {
        initLine();
        initCashiers();
    }

    @FXML
    public void goNext(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.CONGRATULATIONS);
        initCongratulations();
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
        lineR1.setMinWidth(lineR1.getWidth() + (100 * count));
    }

    private void addCostumer(Costumer c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getCostumerWithBasket(c);
        line.getChildren().add(pane);
        line.toBack();
    }

    private void addCashier(Cashier c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_CASHIER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.setCashier(c);
        cashiers.getChildren().add(pane);
        cashiers.toBack();
        PaymentThread p = new PaymentThread(GameStoreGUI.getInstance().getGameStore().getLine(), controller, this);
        p.start();
    }

}
