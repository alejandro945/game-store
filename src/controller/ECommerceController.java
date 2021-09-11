package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Game, Double> tblPointGame;

    @FXML
    private TableColumn<Game, String> tblReviewGame;

    @FXML
    private TableColumn<Game, Integer> tblPriceGame;

    ArrayList<Game> gameM = new ArrayList<>();

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
        gameM = new ArrayList<>();
    }

    public void onTableGames(){
        createGameTest(); //Quitar una vez implementado correctamente
        List<Game> games = gameM; // Llamar a la lista verdadera de la clase gameStore o entra por parametro, Nuevo test
        ObservableList<Game> newGameM;
        newGameM = FXCollections.observableList(games);

        tblGameWish.setItems(newGameM);
        tblCodeGame.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGame.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGame.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblPointGame.setCellValueFactory(new PropertyValueFactory<>("point"));
        tblPriceGame.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    private void createGameTest(){
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


}
