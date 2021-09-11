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
    private TableColumn<Game, Double> tblPointGame;

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
    private TableColumn<Game, Double> tblPointGameClient;

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

    @FXML
    private JFXTextField txtCodeClient;

    @FXML
    private JFXTextField txtnameClient;

    @FXML
    private JFXTextField txtLastNameClient;

    @FXML
    private JFXTextField txtIdClient;

    private GameStoreGUI gameS;

    ArrayList<Game> gameM = new ArrayList<>();
    ArrayList<Game> listWish = new ArrayList<>();

    private int selectGameCodeListWish;
    private int seqClient = 1;

    public ECommerceController (){
    }

    public void intializeEComerce (){
        onTableGames();
        btnEndWishList.setDisable(true);
        paneInformatión.setDisable(true);
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
        listWish = new ArrayList<>();
    }

    public void onTableGames(){
        createGameTest();
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

    public void disableBtnAddorRemove(int code){
        boolean out = false;
            if(!listWish.isEmpty()){
                for (int i = 0; i<listWish.size() && !out; i++){ //Cuando este la lista definitiva solo la llamaremos
                    if(listWish.get(i).getCode() == code){
                        btnAddGameWish.setDisable(true);
                        btnRemoveGameWish.setDisable(false);
                        out = true;
                    } else {
                        btnAddGameWish.setDisable(false);
                        btnRemoveGameWish.setDisable(true);
                    }
                }
                if(listWish.isEmpty()){
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
                        listWish.add(gameM.get(i));
                    }
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
        int toEliminate = 0;
        int total = 0;
        try {
            if (txtamountGame.getText().equals("")){
                toEliminate = 1;
            } else {
                toEliminate = Integer.parseInt(txtamountGame.getText());
            }
            for (int i = 0; i<listWish.size(); i++) {
                if (listWish.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())) {
                total++;
                }
            }
            if(toEliminate <= total){
                int count2 = 0;
                int repet = listWish.size();
                for (int i = 0; i<repet && toEliminate != count2; i++){
                    if(listWish.get(i).getCode() == Integer.parseInt(txtCodeGame.getText())){
                        listWish.remove(i);
                        count2++;
                        i--;
                    }
                }
                GameStoreGUI.getInstance().createAlert("Deletion succesfully", Route.SUCCESS);
                trigger();
            } else {
                GameStoreGUI.getInstance().createAlert("Trying to delete a bigger amount", Route.ERROR);
            }
        } catch (NumberFormatException e){
            GameStoreGUI.getInstance().createAlert("You can not enter letters", Route.WARNING);
        }
    }

    private void trigger(){
        btnRemoveGameWish.setDisable(true);
        btnAddGameWish.setDisable(false);
        txtCodeGame.setText("");
        txtnameGame.setText("");
        txtpointGame.setText("");
        txtpriceGame.setText("");
        lblreviewGame.setText("");
        txtamountGame.setEditable(false);
        txtamountGame.setText("");
    }

    @FXML
    public void btnAddAmount(ActionEvent event) {
        txtamountGame.setEditable(true);
    }

    @FXML
    public void onEndWishList(ActionEvent event) {
        if(!listWish.isEmpty()){
            gameS.toAddClientWithListWish();
            onTableListWishClient();
            txtCodeClient.setText(generateCodeClient());
        } else {
            System.out.println("Debes de seleccionar juegos");
        }
    }

    public void onTableListWishClient(){
     List<Game> gamesWC = listWish;
     ObservableList<Game> newGamesWC;
     newGamesWC = FXCollections.observableList(gamesWC);

     tblGameWishClient.setItems(newGamesWC);
     tblCodeGameClient.setCellValueFactory(new PropertyValueFactory<>("code"));
     tblNameGameClient.setCellValueFactory(new PropertyValueFactory<>("gameName"));
     tblReviewGameClient.setCellValueFactory(new PropertyValueFactory<>("review"));
     tblPointGameClient.setCellValueFactory(new PropertyValueFactory<>("point"));
     tblPriceGameClient.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public String generateCodeClient(){
        String code = seqClient+" ";
        for(int i = 0; i<listWish.size(); i++){
            code += listWish.get(i).getCode()+" ";
        }
        return code;
    }

    @FXML
    public void onAddClient(ActionEvent event) {
        try{
            if(!txtnameClient.getText().equals("") && !txtLastNameClient.getText().equals("")
            && !txtIdClient.getText().equals("")){
                gameS.getGameStore().addClient(txtCodeClient.getText(), txtnameClient.getText(), txtLastNameClient.getText(),
                        Long.parseLong(txtIdClient.getText()), listWish);
                seqClient++;
                System.out.println("CreateClient");
                onBackToListWish(event);
                listWish = new ArrayList<>();
            } else {
                System.out.println("ingresa todos los campos");
            }
        } catch (NumberFormatException e){
            System.out.println("No puedes ingresar letras en la id");
        }
    }

    @FXML
    public void onBackToListWish(ActionEvent event) {
        gameS.toCommerce(event);
    }
}
