package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import model.*;
import routes.*;

public class GameStoreGUI {
    @FXML
    private Pane mainPane;

    @FXML
    private Circle btnCloseLogin;

    @FXML
    private Circle btnMinimize;

    private static GameStoreGUI instance;
    private GameStore gameStore;
    private AdminController aController;

    private GameStoreGUI() {
        aController = new AdminController();
    }

    public static GameStoreGUI getInstance() {
        if (instance == null) {
            instance = new GameStoreGUI();
        }
        return instance;
    }

    public GameStore getGameStore() {
        return gameStore;
    }

    public void setGameStore(GameStore gameStore) {
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

    private Stage getWindow() {
        return (Stage) mainPane.getScene().getWindow();
    }

    public void renderScreen(Route route) {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(route.getRoute()));
        fxmlloader.setController(setController(route));
        try {
            Parent root = fxmlloader.load();
            mainPane.getChildren().setAll(root);
        } catch (IOException e) {
            createAlert("Sorry we could not find your route", Route.ERROR);
        }
    }

    private Object setController(Route route) {
        switch (route) {
            case WELCOME:
                return this;
            case LOGIN:
            return aController; 
            default:
                return null;
        }
    }

    @FXML
    public void init(ActionEvent event) {

    }

    @FXML
    public void toCommerce(ActionEvent event) {

    }

    @FXML
    public void toLogin(ActionEvent event) {
        renderScreen(Route.LOGIN);
    }

    public Stage loadModal(Route route, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(route.getRoute()));
        fxmlLoader.setController(controller);
        Parent modal = fxmlLoader.load();
        Scene scene = new Scene(modal);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;
    }

    public void createAlert(String message, Route alertType) {
        JFXAlert<String> newAlert = new JFXAlert<>();
        newAlert.initModality(Modality.APPLICATION_MODAL);
        newAlert.setOverlayClose(false);
        // GIF RENDERING
        ImageView gif = new ImageView(new Image(alertType.getRoute()));
        gif.setFitHeight(80);
        gif.setFitWidth(80);
        // ALERT-CONTENT
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.getStylesheets().add(Route.STYLES.getRoute());
        layout.setHeading(new Label("Game Store Alerts"));
        layout.setBody(new HBox(8, new Label(message), gif));
        // ACTIONS
        JFXButton cancelButton = new JFXButton("OK");
        cancelButton.setButtonType(JFXButton.ButtonType.RAISED);
        cancelButton.setCancelButton(true);
        cancelButton.setStyle(Route.STYLES.getRoute());
        cancelButton.setOnAction(closeEvent -> newAlert.hideWithAnimation());
        layout.setActions(cancelButton);
        // STANGING
        Stage stage = (Stage) newAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:" + Route.ICON.getRoute()));
        newAlert.setContent(layout);
        newAlert.showAndWait();
    }

}
