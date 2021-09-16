package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Costumer;
import model.Game;
import model.GameStore;
import routes.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class StartController {

    public StartController(){
    }

    @FXML
    private JFXComboBox<String> cbxSelectClient;

    @FXML
    private Label lblCodeClient;

    @FXML
    public void onAddClienttoShop(ActionEvent event) {
        if(cbxSelectClient.getValue() != null){
            if(GameStoreGUI.getInstance().getGameStore().addClientsToShop(searchClient(cbxSelectClient.getValue().toString()))){
                GameStoreGUI.getInstance().createAlert("Added Client", Route.SUCCESS);
            } else {
                GameStoreGUI.getInstance().createAlert("The client has already been added", Route.ERROR);
            }
        } else {
            GameStoreGUI.getInstance().createAlert("Please select an client", Route.ERROR);
        }
    }

    public Costumer searchClient (String name){
        for (int i = 0; i<GameStoreGUI.getInstance().getGameStore().getCostumers().size(); i++){
            if(GameStoreGUI.getInstance().getGameStore().getCostumers().get(i).getName().equals(name)){
                return GameStoreGUI.getInstance().getGameStore().getCostumers().get(i);
            }
        }
        return null;
    }

    public void initializeStartComerce(){
        onDataCBXClient();
    }

    public void onDataCBXClient(){
        try {
            List<String> clientName = new ArrayList<>();
            for (int i = 0; i<GameStoreGUI.getInstance().getGameStore().getCostumers().size(); i++){
                clientName.add(GameStoreGUI.getInstance().getGameStore().getCostumers().get(i).getName());
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
