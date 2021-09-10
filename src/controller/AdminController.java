package controller;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import routes.Route;

public class AdminController {
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    @FXML
    private TableView<?> tbGames;

    @FXML
    private JFXTextField txtCashier;

    @FXML
    private TableView<?> tbShelves;

    @FXML
    private Button edit;

    @FXML
    private JFXTextField lblCashier;

    @FXML
    private TableView<?> tbCostumers;

    @FXML
    public void createGame(ActionEvent event) {

    }

    @FXML
    public void createShelve(ActionEvent event) {

    }

    @FXML
    public void editCashier(ActionEvent event) {

    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    @FXML
    public void signIn(ActionEvent event) {

    }
}
