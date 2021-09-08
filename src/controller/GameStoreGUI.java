package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import model.GameStore;

public class GameStoreGUI {
    @FXML
    private Pane mainPane;

    @FXML
    private Circle btnCloseLogin;

    @FXML
    private Circle btnMinimize;

    private GameStore gameStore;

    public GameStoreGUI(GameStore gameStore) {
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

}
