package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import routes.Route;
import model.Game;

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
    private JFXTextField lblCashier;

    @FXML
    private TableView<?> tbCostumers;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXTextArea review;

    private Stage modal;

    @FXML
    public void createGame(ActionEvent event) {
        if (modal == null) {
            modal = GameStoreGUI.getInstance().loadModal(Route.GAMEMODAL, this);
            modal.show();
        }
    }

    @FXML
    public void saveGame(ActionEvent event) {

    }

    @FXML
    void cancelModal(ActionEvent event) {
        modal.close();
        modal = null;
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
        GameStoreGUI.getInstance().renderScreen(Route.ADMDASH);
    }

    // ---------------------------------------CREATE-SHELVE----------------------------------------------

    @FXML
    private JFXTextField shelveName;

    @FXML
    private JFXComboBox<Game> chooseGame;

    @FXML
    public void chooseGame(ActionEvent event) {

    }

    @FXML
    public void cancelModalShelve(ActionEvent event) {

    }

    @FXML
    public void saveGameShelve(ActionEvent event) {

    }
}
