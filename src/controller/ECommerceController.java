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
    private TableColumn<Game, String> tblShelveGame;

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

    ArrayList<Game> listWish = new ArrayList<>();

    public ECommerceController() {
    }

    public void intializeEComerce() {
        onTableGames();
        listWish = new ArrayList<>();
        btnEndWishList.setDisable(true);
        paneInformatión.setDisable(true);
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    public void onTableGames() {
        List<Game> dispGame = new ArrayList<>();
        for (int i = 0; i<GameStoreGUI.getInstance().getGameStore().getGames().size(); i++){
            if(GameStoreGUI.getInstance().getGameStore().getGames().get(i).getAmount() != 0){
                dispGame.add(GameStoreGUI.getInstance().getGameStore().getGames().get(i));
            }
        }

        ObservableList<Game> gameList = FXCollections
                .observableList(dispGame);
        tblGameWish.setItems(gameList);
        tblCodeGame.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGame.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGame.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblPriceGame.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblShelveGame.setCellValueFactory(new PropertyValueFactory<>("shelveName"));
    }

    public void disableBtnAddorRemove(int code) {
        boolean out = false;
        if (!listWish.isEmpty()) {
            for (int i = 0; i < listWish.size() && !out; i++) {
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
        List<Game> g = GameStoreGUI.getInstance().getGameStore().getGames();
        int countadd = 0;
        for (int i = 0; i < g.size(); i++) {
            if (g.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                try {
                    countadd = getAmount();
                    for (int j = 0; j < countadd; j++) {
                        listWish.add(g.get(i));
                    }
                    paneInformatión.setDisable(true);
                    GameStoreGUI.getInstance().createAlert("Game added succesfully", Route.SUCCESS);
                    btnEndWishList.setDisable(false);
                } catch (NumberFormatException e) {
                    GameStoreGUI.getInstance().createAlert("You can not enter letters", Route.ERROR);
                    txtamountGame.setText("");
                }
            }
        }
        trigger();
    }

    private int getAmount() throws NumberFormatException {
        int render = 0;
        if (txtamountGame.getText().equals("0") || txtamountGame.getText().equals("")) {
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
                paneInformatión.setDisable(true);
                if(listWish.size()==0){
                    btnEndWishList.setDisable(true);
                }
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
                txtamountGame.setText("0");
                btnAddGameWish.setDisable(false);
                paneInformatión.setDisable(false);
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
        String code = GameStoreGUI.getInstance().getGameStore().getCostumers().size() + 1 + " ";
        for (int i = 0; i < listWish.size(); i++) {
            code += listWish.get(i).getCode() + " ";
        }
        return code;
    }

    @FXML
    public void onAddClient(ActionEvent event) {
        if (!txtnameClient.getText().equals("")) {
            GameStoreGUI.getInstance().getGameStore().addClient(Integer.parseInt(txtCodeClient.getText().split(" ")[0]),
                    txtCodeClient.getText(), txtnameClient.getText(), listWish);
            GameStoreGUI.getInstance().createAlert("Costumer added Succesfully", Route.SUCCESS);
            onBackToListWish(event);
            listWish = new ArrayList<>();
        } else {
            GameStoreGUI.getInstance().createAlert("Complete all Fields", Route.WARNING);
        }
    }

    @FXML
    public void onBackToListWish(ActionEvent event) {
        GameStoreGUI.getInstance().toCommerce(event);
    }
}
