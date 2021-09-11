package controller;

import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Game;

import routes.Route;

public class AdminController {

    // -----------------------------------------------ADMIN-DASH-----------------------------

    private Stage modal;

    @FXML
    public void cancelModal(ActionEvent event) {
        modal.close();
        modal = null;
    }

    @FXML
    public void createShelve(ActionEvent event) {
        if (modal == null) {
            modal = GameStoreGUI.getInstance().loadModal(Route.SHELVEMODAL, this);
            initializePrueba();
            modal.show();
        }
    }

    @FXML
    public void createGame(ActionEvent event) {
        if (modal == null) {
            modal = GameStoreGUI.getInstance().loadModal(Route.GAMEMODAL, this);
            modal.show();
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    // ---------------------------------------SING-IN------------------------------------------
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    @FXML
    public void signIn(ActionEvent event) {
        if (userName.getText().equals("root") && password.getText().equals("root")) {
            GameStoreGUI.getInstance().renderScreen(Route.ADMDASH);
        } else {
            GameStoreGUI.getInstance().createAlert("You don´t are admin", Route.ERROR);
        }
    }

    // --------------------------------------CREATE-GAMES--------------------------------------------

    @FXML
    private TableView<Game> tbGames;

    @FXML
    private TableColumn<?, ?> tblIdGames;

    @FXML
    private TableColumn<?, ?> tblNameGames;

    @FXML
    private TableColumn<?, ?> tblAmountGames;

    @FXML
    private TableColumn<?, ?> tblPriceGames;

    @FXML
    private TableColumn<?, ?> tblReviewGames;

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

    public Boolean validateFields() {
        if (price.getText().equals("") || amount.getText().equals("") || review.getText().equals("")
                || title.getText().equals("")) {
            return false;
        } else {
            return true;
        }

    }

    @FXML
    public void saveGame(ActionEvent event) {
        if (!validateFields()) {
            try {
                GameStoreGUI.getInstance().getGameStore().addGames(title.getText(), review.getText(),
                        Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()));
                ;
            } catch (NumberFormatException e) {
                GameStoreGUI.getInstance().createAlert("You only can write numbers!", Route.WARNING);
            }
        }
    }

    public void initializeGamesTable() {
        ObservableList<Game> newGame = FXCollections
                .observableArrayList(GameStoreGUI.getInstance().getGameStore().getGames());

        tbGames.setItems(newGame);
        tblIdGames.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGames.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGames.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblAmountGames.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblPriceGames.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    // ---------------------------------------CREATE-SHELVE----------------------------------------------

    @FXML
    private TableView<?> tbShelves;

    @FXML
    private TableColumn<?, ?> tblIdShelves;

    @FXML
    private TableColumn<?, ?> tblGamesShelves;

    @FXML
    private TableColumn<?, ?> tblAmountShelves;

    @FXML
    private JFXTextField shelveName;

    @FXML
    private JFXComboBox<?> chooseGame;

    @FXML
    private JFXChipView<String> txtChip;

    @FXML
    public void chooseGame(ActionEvent event) {

    }

    @FXML
    public void cancelModalShelve(ActionEvent event) {
        modal.close();
        modal = null;
    }

    @FXML
    public void saveGameShelve(ActionEvent event) {

    }

    public void initializePrueba() {
        txtChip.getChips().addAll("HOLA", "COMO", "VARELAMK", "SUAREZMK", "MURCIAMK");

    }

    // ----------------------------------VIEW-COSTUMERS----------------------------------------

    @FXML
    private TableView<?> tbCostumers;

    @FXML
    private TableColumn<?, ?> tblIdCostumer;

    @FXML
    private TableColumn<?, ?> tblGamesCostumers;

    @FXML
    private TableColumn<?, ?> tblAmountCostumers;

    // ----------------------------------------CASHIERS----------------------------------------------

    @FXML
    private JFXTextField txtCashier;

    @FXML
    private JFXTextField lblCashier;

    @FXML
    public void editCashier(ActionEvent event) {

    }

}
