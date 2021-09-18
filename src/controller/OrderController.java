package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.Costumer;
import model.Game;
import routes.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    @FXML
    private JFXRadioButton rbOrderInsertion;

    @FXML
    private ToggleGroup Order;

    @FXML
    private JFXRadioButton rbOrderBubble;

    @FXML
    private Pane pInfoClient;

    @FXML
    private JFXButton btnNextClient;

    private int clientOrder = 0;

    @FXML
    public void onOrderGames(ActionEvent event) throws IOException {
        if(orderSelected(getArray().get(clientOrder))){
            clientOrder++;
            if(clientOrder < getArray().size()){
                addNode(getArray().get(clientOrder));
            } else {
                btnNextClient.setText("Next Secction");
            }
        }
    }

    public void initializaOrderClient() throws IOException {
        clientOrder = 0;
        addNode(getArray().get(0));
    }

    public List<Costumer> getArray() throws IOException {
        List<Costumer> getClientstoQueue = GameStoreGUI.getInstance().getGameStore().getLine().convertQueueToArr();
        return getClientstoQueue;
    }

    private void addNode(Costumer c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        CostumerController controller = new CostumerController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getData(c);
        pInfoClient.getChildren().add(pane);
        pInfoClient.toBack();
    }

    public boolean orderSelected(Costumer c) {
        boolean out = false;
        if (rbOrderInsertion.isSelected()) {
            GameStoreGUI.getInstance().getGameStore().insertionSort(c);
            out = true;
        } else if (rbOrderBubble.isSelected()) {
            GameStoreGUI.getInstance().getGameStore().bubbleSort(c);
            out = true;
        } else {
            GameStoreGUI.getInstance().createAlert("Please select a method to order", Route.ERROR);
            out = false;
        }
        return out;
    }
}
