package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import routes.Route;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ECommerceController {

    @FXML
    private TableView<Game> tblGameWish;

    @FXML
    private TableColumn<Game, Integer> tblCodeGame;

    @FXML
    private TableColumn<Game, String> tblNameGame;

    @FXML
    private TableColumn<Game, String> tblReviewGame;

    @FXML
    private TableColumn<Game, Integer> tblPriceGame;

    @FXML
    private TableView<Game> tblGameWishClient;

    @FXML
    private TableColumn<Game, Integer> tblCodeGameClient;

    @FXML
    private TableColumn<Game, String> tblNameGameClient;

    @FXML
    private TableColumn<Game, String> tblReviewGameClient;

    @FXML
    private TableColumn<Game, Integer> tblPriceGameClient;

    @FXML
    private Pane paneInformatión;

    @FXML
    private JFXButton btnEndWishList;

    @FXML
    private JFXButton btnStartListWish;

    @FXML
    private JFXTextField txtCodeGame;

    @FXML
    private JFXTextField txtnameGame;

    @FXML
    private JFXTextField txtpriceGame;

    @FXML
    private Label lblreviewGame;

    @FXML
    private JFXButton btnAddGameWish;

    @FXML
    private JFXButton btnRemoveGameWish;

    @FXML
    private JFXTextField txtamountGame;

    @FXML
    private JFXTextField txtCodeClient;

    @FXML
    private JFXTextField txtnameClient;

    ArrayList<Game> gameM = new ArrayList<>(); // Testing
    ArrayList<Game> listWish = new ArrayList<>();

    private int seqClient = 1;

    public ECommerceController() {
    }

    public void intializeEComerce() {
        onTableGames();
        listWish.clear();
        btnEndWishList.setDisable(true);
        paneInformatión.setDisable(true);
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
        listWish.clear();
    }

    public void onTableGames() {
        createGameTest();
        List<Game> games = gameM; // Llamar a la lista verdadera de la clase gameStore o entra por parametro,
                                  // Nuevo test
        ObservableList<Game> newGameM;
        newGameM = FXCollections.observableList(games);

        tblGameWish.setItems(newGameM);
        tblCodeGame.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGame.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGame.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblPriceGame.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    // Test
    private void createGameTest() {
        Game g1 = new Game(1, "g1", "saoa", 2000, 2);
        Game g2 = new Game(2, "g2", "saoa", 2100, 1);
        Game g3 = new Game(3, "g3", "saoa", 1800, 6);
        Game g4 = new Game(4, "g4", "saoa", 5000, 5);
        Game g5 = new Game(5, "g5", "saoa", 204, 9);
        gameM.add(g1);
        gameM.add(g2);
        gameM.add(g3);
        gameM.add(g4);
        gameM.add(g5);
    }

    @FXML
    public void onStartListWish(ActionEvent event) {
        paneInformatión.setDisable(false);
        btnStartListWish.setDisable(true);
        btnEndWishList.setDisable(false);
    }

    public void disableBtnAddorRemove(int code) {
        boolean out = false;
        if (!listWish.isEmpty()) {
            for (int i = 0; i < listWish.size() && !out; i++) { // Cuando este la lista definitiva solo la llamaremos
                if (listWish.get(i).getCode() == code) {
                    btnAddGameWish.setDisable(true);
                    btnRemoveGameWish.setDisable(false);
                    out = true;
                } else {
                    btnAddGameWish.setDisable(false);
                    btnRemoveGameWish.setDisable(true);
                }
            }
            if (listWish.isEmpty()) {
                btnRemoveGameWish.setDisable(true);
            }
        }
    }

    @FXML
    public void onAddGameListWish(ActionEvent event) {
        int countadd = 0;
        for (int i = 0; i < gameM.size(); i++) {
            if (gameM.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                try {
                    countadd = getAmount();
                    for (int j = 0; j < countadd; j++) {
                        listWish.add(gameM.get(i));
                    }
                    btnAddGameWish.setDisable(true);
                    btnRemoveGameWish.setDisable(false);
                    trigger();
                    GameStoreGUI.getInstance().createAlert("Game added succesfully", Route.SUCCESS);
                } catch (NumberFormatException e) {
                    GameStoreGUI.getInstance().createAlert("You can not enter letters", Route.ERROR);
                    txtamountGame.setText("");
                }
            }
        }
    }

    private int getAmount() throws NumberFormatException {
        int render = 0;
        if (txtamountGame.getText().equals("")) {
            render = 1;
        } else {
            render = Integer.parseInt(txtamountGame.getText());
        }
        return render;
    }

    @FXML
    public void onRemoveGameListWish(ActionEvent event) {
        int toEliminate = 0;
        int total = 0;
        try {
            toEliminate = getAmount();
            for (int i = 0; i < listWish.size(); i++) {
                if (listWish.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                    total++;
                }
            }
            if (toEliminate <= total) {
                int count = 0;
                int repet = listWish.size();
                for (int i = 0; i < repet && toEliminate != count; i++) {
                    if (listWish.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                        listWish.remove(i);
                        count++;
                        i--;
                    }
                }
                GameStoreGUI.getInstance().createAlert("Deletion succesfully", Route.SUCCESS);
                btnRemoveGameWish.setDisable(true);
                btnAddGameWish.setDisable(false);
                trigger();
            } else {
                GameStoreGUI.getInstance().createAlert("Trying to delete a bigger amount", Route.ERROR);
            }
        } catch (NumberFormatException e) {
            GameStoreGUI.getInstance().createAlert("You can not enter letters", Route.WARNING);
        }
    }

    private void trigger() {
        txtCodeGame.setText("");
        txtnameGame.setText("");
        txtpriceGame.setText("");
        lblreviewGame.setText("");
        txtamountGame.setText("");
    }

    @FXML
    public void onSelectGame(MouseEvent event) {
        Game gameSelected;
        if (event.getClickCount() == 2) {
            gameSelected = tblGameWish.getSelectionModel().getSelectedItem();
            if (gameSelected != null) {
                disableBtnAddorRemove(gameSelected.getCode());
                GameStoreGUI.getInstance().createAlert("Selected", Route.SUCCESS);
                txtCodeGame.setText(gameSelected.getCode() + "");
                txtnameGame.setText(gameSelected.getGameName());
                txtpriceGame.setText(gameSelected.getPrice() + "");
                lblreviewGame.setText(gameSelected.getReview());
                txtamountGame.setText("");
            }
        }
    }

    @FXML
    public void onEndWishList(ActionEvent event) {
        if (!listWish.isEmpty()) {
            GameStoreGUI.getInstance().renderScreen(Route.ADDCOSTUMER);
            onTableListWishClient();
            txtCodeClient.setText(generateCodeClient());
        } else {
            GameStoreGUI.getInstance().createAlert("We need at least one game in your list", Route.WARNING);
        }
    }

    public void onTableListWishClient() {
        List<Game> gamesWC = listWish;
        ObservableList<Game> newGamesWC;
        newGamesWC = FXCollections.observableList(gamesWC);

        tblCodeGameClient.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGameClient.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGameClient.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblPriceGameClient.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblGameWishClient.setItems(newGamesWC);
    }

    public String generateCodeClient() {
        String code = seqClient + " ";
        for (int i = 0; i < listWish.size(); i++) {
            code += listWish.get(i).getCode() + " ";
        }
        return code;
    }

    @FXML
    public void onAddClient(ActionEvent event) {
        if (!txtnameClient.getText().equals("")) {
            GameStoreGUI.getInstance().getGameStore().addClient(txtCodeClient.getText(), txtnameClient.getText(),
                    listWish);
            seqClient++;
            GameStoreGUI.getInstance().createAlert("Costumer added Succesfully", Route.SUCCESS);
            onBackToListWish(event);
        } else {
            GameStoreGUI.getInstance().createAlert("Complete all Fields", Route.WARNING);
        }
    }

    @FXML
    public void onBackToListWish(ActionEvent event) {
        GameStoreGUI.getInstance().toCommerce(event);
    }
}
