package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Costumer;
import model.Game;
import model.GameStore;
import model.Shelve;
import routes.Route;

import java.io.IOException;
import java.util.List;

public class OrderController {

    @FXML
    private JFXRadioButton rbOrderSelection;

    @FXML
    private ToggleGroup Order;

    @FXML
    private JFXRadioButton rbOrderBubble;

    @FXML
    private Pane pInfoClient;

    @FXML
    private JFXButton btnNextClient;

    @FXML
    private Button next;

    @FXML
    private HBox line;

    @FXML
    private VBox shevels;

    @FXML
    private AnchorPane lineR;

    @FXML
    private AnchorPane shelveR;

    private int clientOrder = 0;

    @FXML
    public void goNext(ActionEvent event) throws IOException {
        GameStoreGUI.getInstance().renderScreen(Route.SECTION3);
        GameStoreGUI.getInstance().showCostumerTime();
    }

    @FXML
    public void onOrderGames(ActionEvent event) throws IOException {
        if (orderSelected(getArray().get(clientOrder))) {
            clientOrder++;
            if (clientOrder < getArray().size()) {
                addCostumer(getArray().get(clientOrder), 1);
            } else {
                btnNextClient.setDisable(true);
                next.setDisable(false);
                initLine();
            }
            rbOrderBubble.setSelected(false);
            rbOrderSelection.setSelected(false);
        }
    }

    public void initializaOrderClient() throws IOException {
        clientOrder = 0;
        addCostumer(getArray().get(0), 1);
        initShelves();
        initLine();
    }

    private void initShelves() throws IOException {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        shevels.setSpacing(12);
        int count = 0;
        for (Shelve s : g.getShelves()) {
            for (int i = 0; i < s.getGameShelve().getMax(); i++) {
                if (s.getGameShelve().getRack(i) != null) {
                    String slot = s.getNameShelve() + " - " + i;
                    addRack(slot, s.getGameShelve().getRack(i));
                }
                count++;
            }
        }
        shelveR.setMinHeight(shelveR.getHeight() + (50 * count));
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
        lineR.setMinWidth(lineR.getWidth() + (160 * count));
    }

    public List<Costumer> getArray() throws IOException {
        List<Costumer> getClientstoQueue = GameStoreGUI.getInstance().getGameStore().getLine().convertQueueToArr();
        return getClientstoQueue;
    }

    private void addCostumer(Costumer c, int type) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getCostumer(c);
        if (type == 1) {
            pInfoClient.getChildren().add(pane);
            pInfoClient.toBack();
        } else {
            line.getChildren().add(pane);
            line.toBack();
        }
    }

    private void addRack(String slot, Game g) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_RACK.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getRack(slot, g);
        shevels.getChildren().add(pane);
        shevels.toBack();
    }

    public boolean orderSelected(Costumer c) {
        boolean out = false;
        if (rbOrderSelection.isSelected()) {
            GameStoreGUI.getInstance().getGameStore().selectionSort(c);
            out = true;
        } else if (rbOrderBubble.isSelected()) {
            GameStoreGUI.getInstance().getGameStore().bubbleSort(c);
            out = true;
        } else {
            GameStoreGUI.getInstance().createAlert("Please select a method to order", Route.ERROR);
            out = false;
        }
        return out;
    }
}
