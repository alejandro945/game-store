package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Costumer;
import model.GameStore;
import routes.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private List<Costumer> cLine;

    public StartController() {

    }

    @FXML
    private TableView<Costumer> tbCostumer;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> games;

    @FXML
    private JFXComboBox<String> cbxSelectClient;

    @FXML
    private AnchorPane lineR;

    @FXML
    private HBox line;

    @FXML
    private Button next;

    @FXML
    public void onAddClienttoShop(ActionEvent event) {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        if (cbxSelectClient.getValue() != null) {
            if (g.addCostumerToLine(g.searchCostumer(cbxSelectClient.getValue().toString()))) {
                GameStoreGUI.getInstance().createAlert("Added Client", Route.SUCCESS);
                initializeStartComerce();
                refreshLine();
                next.setDisable(false);
            } else {
                GameStoreGUI.getInstance().createAlert("The client has already been added", Route.ERROR);
            }
        } else {
            GameStoreGUI.getInstance().createAlert("Please select a client", Route.WARNING);
        }
    }

    public void initializeStartComerce() {
        onDataCBXClient();
        cLine = GameStoreGUI.getInstance().getGameStore().getLine().convertQueueToArr();
        ObservableList<Costumer> line = FXCollections.observableList(cLine);
        tbCostumer.setItems(line);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        games.setCellValueFactory(new PropertyValueFactory<>("games"));
    }

    private void refreshLine() {
        line.getChildren().clear();
        line.setSpacing(12);
        line.setPadding(new Insets(10));
        for (Costumer costumer : cLine) {
            try {
                addNode(costumer);
                lineR.setMinWidth(lineR.getWidth()+40);
            } catch (IOException e) {
                GameStoreGUI.getInstance().createAlert("Opps Screen not found it", Route.ERROR);
            }
        }
    }

    private void addNode(Costumer c) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Route.NODE_COSTUMER.getRoute()));
        NodeController controller = new NodeController();
        fxmlLoader.setController(controller);
        Pane pane = fxmlLoader.load();
        controller.getCostumer(c);
        line.getChildren().add(pane);
    }

    public void onDataCBXClient() {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        try {
            List<String> clientName = new ArrayList<>();
            for (Costumer c : g.getCostumers()) {
                clientName.add(c.getName());
            }
            ObservableList<String> obs = FXCollections.observableArrayList(clientName);
            cbxSelectClient.setItems(obs);
        } catch (NullPointerException e) {
            GameStoreGUI.getInstance().createAlert("Sorry we do not have costumers yet", Route.WARNING);
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    @FXML
    public void goNext(ActionEvent event) throws IOException {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        if (!g.getLine().isEmpty()) {
            GameStoreGUI.getInstance().renderScreen(Route.SECTION2);
            GameStoreGUI.getInstance().showCostumer();
        }
    }
}
