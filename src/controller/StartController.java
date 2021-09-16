package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.GameStore;
import routes.Route;

import java.util.ArrayList;
import java.util.List;

public class StartController {

    public StartController() {
    }

    @FXML
    private JFXComboBox<String> cbxSelectClient;

    @FXML
    private Label lblCodeClient;

    @FXML
    public void onAddClienttoShop(ActionEvent event) {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        if (cbxSelectClient.getValue() != null) {
            if (g.addCostumerToLine(g.searchCostumer(cbxSelectClient.getValue().toString()))) {
                GameStoreGUI.getInstance().createAlert("Added Client", Route.SUCCESS);
            } else {
                GameStoreGUI.getInstance().createAlert("The client has already been added", Route.ERROR);
            }
        } else {
            GameStoreGUI.getInstance().createAlert("Please select an client", Route.ERROR);
        }
    }

    public void initializeStartComerce() {
        onDataCBXClient();
    }

    public void onDataCBXClient() {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        try {
            List<String> clientName = new ArrayList<>();
            for (int i = 0; i < g.getCostumers().size(); i++) {
                clientName.add(g.getCostumers().get(i).getName());
            }
            ObservableList<String> obs;
            obs = FXCollections.observableArrayList(clientName);
            cbxSelectClient.setItems(obs);
        } catch (NullPointerException e) {
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }
}
