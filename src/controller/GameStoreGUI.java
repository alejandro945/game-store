package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import model.GameStore;
import routes.Route;

public class GameStoreGUI {
    @FXML
    private Pane mainPane;

    @FXML
    private Circle btnCloseLogin;

    @FXML
    private Circle btnMinimize;

    private static GameStoreGUI instance;
    private GameStore gameStore;

    private GameStoreGUI() {
    }

    public static GameStoreGUI getInstance() {
        if (instance == null) {
            instance = new GameStoreGUI();
        }
        return instance;
    }

    public GameStore getGameStore(){
        return gameStore;
    }

    public void setGameStore(GameStore gameStore){
        this.gameStore = gameStore;
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
        if (event.getSource() == btnCloseLogin) {
            System.exit(0);
        } else if (event.getSource() == btnMinimize) {
            getWindow().setIconified(true);
        }
    }

    public Stage getWindow() {
        return (Stage) mainPane.getScene().getWindow();
    }

    public void renderScreen(Route route) throws IOException, InterruptedException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(route.getRoute()));
     //   fxmlloader.setController(setController(route));
        Parent root = fxmlloader.load();
        mainPane.getChildren().setAll(root);
    }

}
