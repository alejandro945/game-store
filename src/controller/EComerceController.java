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
import java.util.Observable;

public class EComerceController {

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
    private JFXTextField txtpointGame;

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


    private GameStoreGUI gameS;
    private GameStore game;

    private int selectGameCodeListWish;
    ArrayList<Game> gameM = new ArrayList<>(); // Quitar cuando este el arreglo verdadero

    public void intializeEComerce (){
        onTableGames();
        btnEndWishList.setDisable(true);
        paneInformatión.setDisable(true);
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
        game.initListWish();
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

    //Quitar despues de tener la lista de juegos verdadera
    private void createGameTest(){
        Game g1 = new Game(1, "g1", "saoa", 4.5, 2000, 2);
        Game g2 = new Game(2, "g2", "saoa", 4.2, 2100, 1);
        Game g3 = new Game(3, "g3", "saoa", 4.3, 1800, 6);
        Game g4 = new Game(4, "g4", "saoa", 4.1, 5000, 5);
        Game g5 = new Game(5, "g5", "saoa", 4, 204, 9);
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

    @FXML
    public void onSelectGame(MouseEvent event) {
        Game gameSelected;
        if (event.getClickCount() == 2) {
            gameSelected = tblGameWish.getSelectionModel().getSelectedItem();
            if (gameSelected != null) {
                disableBtnAddorRemove(gameSelected.getCode());
                //gameS.createAlert("Selected game", Route.SUCCESS); Falta arreglar
                System.out.println("Seleccionado"); //Quitar cuando las alertas esten buenas
                selectGameCodeListWish = gameSelected.getCode();
                txtCodeGame.setText(gameSelected.getCode() + "");
                txtnameGame.setText(gameSelected.getGameName());
                txtpointGame.setText(gameSelected.getPoint() + "");
                txtpriceGame.setText(gameSelected.getPrice() + "");
                lblreviewGame.setText(gameSelected.getReview());
                txtamountGame.setEditable(false);
                txtamountGame.setText("");
            }
        }
    }

    public void disableBtnAddorRemove(int code){
        boolean out = false;
        if(!game.getListWish().isEmpty()){
            for (int i = 0; i<game.getListWish().size() && !out; i++){ //Cuando este la lista definitiva solo la llamaremos
                if(game.getListWish().get(i).getCode() == code){
                    btnAddGameWish.setDisable(true);
                    btnRemoveGameWish.setDisable(false);
                    out = true;
                } else {
                    btnAddGameWish.setDisable(false);
                    btnRemoveGameWish.setDisable(true);
                }
            }
            if(game.getListWish().isEmpty()){
                btnRemoveGameWish.setDisable(true);
            }
        }
    }

    @FXML
    public void onAddGameListWish(ActionEvent event) {
        int countadd = 0;
        for (int i = 0; i<gameM.size(); i++){
            if(gameM.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())){
                try {
                    if(txtamountGame.getText().equals("") || Integer.parseInt(txtamountGame.getText()) == 1){
                        countadd = 1;
                    } else {
                        countadd = Integer.parseInt(txtamountGame.getText());
                    }
                    for(int j = 0; j<countadd; j++){
                        game.getListWish().add(gameM.get(i));
                    }
                    test();
                    btnAddGameWish.setDisable(true);
                    btnRemoveGameWish.setDisable(false);
                    txtamountGame.setEditable(false);
                    txtamountGame.setText("");
                    System.out.println("Agregado"); //Cambiar cuando las alertas funcionen
                } catch (NumberFormatException e) {
                    System.out.println("No puedes ingresar letas en la cantidad"); //Cambiar cuandos las alertas funcionen
                    txtamountGame.setText("");
                    txtamountGame.setEditable(false);
                }

            }
        }
    }

    @FXML
    public void onRemoveGameListWish(ActionEvent event) {
        boolean out = false;
        int toEliminate = 0;
        int total = 0;
        try {
            if (txtamountGame.getText().equals("")){
                toEliminate = 1;
            } else {
                toEliminate = Integer.parseInt(txtamountGame.getText());
            }
            for (int i = 0; i<game.getListWish().size(); i++) {
                if (game.getListWish().get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                total++;
                }
            }
            if(toEliminate <= total){
                int count2 = 0;
                int repet = game.getListWish().size();
                for (int i = 0; i<repet && toEliminate != count2; i++){
                    if(game.getListWish().get(i).getCode() == Integer.parseInt(txtCodeGame.getText())){
                        game.getListWish().remove(i);
                        count2++;
                        i--;
                    }
                }
                System.out.println("Eliminado"); //Cambiar cuando las alertas funcionen
                test();
                btnRemoveGameWish.setDisable(true);
                btnAddGameWish.setDisable(false);
                txtCodeGame.setText("");
                txtnameGame.setText("");
                txtpointGame.setText("");
                txtpriceGame.setText("");
                lblreviewGame.setText("");
                txtamountGame.setEditable(false);
                txtamountGame.setText("");
            } else {
                System.out.println("Tratas de eliminar una cantidad mayor a la que tienes"); //Acomodar alerta
            }
        } catch (NumberFormatException e){
            System.out.println("No puedes ingresar letras"); //Alertas faltan
        }
    }

    public void test (){
        for (int i = 0; i<game.getListWish().size(); i++){
            System.out.println(game.getListWish().get(i).getGameName());
        }
    }

    @FXML
    public void btnAddAmount(ActionEvent event) {
        txtamountGame.setEditable(true);
    }
}
