package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Costumer;
import model.GameStore;
import routes.Route;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class TimeController {

    @FXML
    private Pane pInformationClient;

    @FXML
    private JFXButton btnUpdateTime;

    @FXML
    private JFXTextField txtNewTime;

    @FXML
    private JFXButton btnSeeLastFinal;

    @FXML
    private Button next;

    @FXML
    private AnchorPane lineR;

    @FXML
    private HBox line;

    private int clientOrder = 0;

    @FXML
    public void onUpdateTime(ActionEvent event) throws IOException {
        if (clientOrder == 0){
            setTimeGetGames(getArray().get(clientOrder));
            convertToStackGame(getArray().get(clientOrder));
            clientOrder++;
            addCostumer(getArray().get(clientOrder));
            int newTime = getArray().get(clientOrder).getTimeInShop() + getArray().get(clientOrder).getWishList().size();
            txtNewTime.setText(newTime + "");
        } else {
            if (clientOrder < getArray().size()-1) {
                addCostumer(getArray().get(clientOrder));
                setTimeGetGames(getArray().get(clientOrder));
                convertToStackGame(getArray().get(clientOrder));
                clientOrder++;
            } else {
                btnUpdateTime.setDisable(true);
                btnSeeLastFinal.setDisable(false);
                next.setDisable(false);
            }

        }
    }

    public void initTimeUpdate() throws IOException {
        clientOrder = 0;
        addCostumer(getArray().get(0));
        int newTime = getArray().get(0).getTimeInShop() + getArray().get(0).getWishList().size();
        txtNewTime.setText(newTime+"");
        btnSeeLastFinal.setDisable(true);
        next.setDisable(true);
    }

    private void addCostumer(Costumer c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getCostumer(c);
        pInformationClient.getChildren().add(pane);
        pInformationClient.toBack();
    }

    public List<Costumer> getArray() {
        List<Costumer> getClientstoQueue = GameStoreGUI.getInstance().getGameStore().getLine().convertQueueToArr();
        return getClientstoQueue;
    }

    public void setTimeGetGames(Costumer c) {
        for (int i = 0; i<getArray().size(); i++){
            if (getArray().get(i).equals(c.getName())){
                c.setTime(c.getTimeInShop()+c.getWishList().size());
            }
        }
    }

    public void convertToStackGame(Costumer c){
        for (int i = 0; i<getArray().size(); i++){
            if(getArray().get(i).getName().equals(c.getName())){
                c.getShopBasket().convertArrtoStack(getArray().get(i).getWishList());
            }
        }
    }

    @FXML
    public void onNext(ActionEvent event) {
        System.out.println("Acabe hpta");
    }


    @FXML
    public void onSeeAllList(ActionEvent event) {
        /*
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        line.setSpacing(12);
        for (int i = 0; i<getArray().size(); i++) {

        }
        
         */
    }

    /*
    private void initShelves() throws IOException{
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        shevels.setSpacing(12);
        for (Game game : g.getGames()) {
            String slot = game.getShelveName() + " - " + g.searchShelve(game.getShelveName()).getGameShelve().getIndexInTable(game.getCode());
            addRack(slot, game);
            shelveR.setMinHeight(shelveR.getHeight()+60);
        }
    }
     */
}
