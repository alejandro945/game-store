package controller;

import java.io.IOException;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private ECommerceController eController;
    private StartController sController;
    private OrderController oController;
    private TimeController tController;

    private GameStoreGUI() {
        aController = new AdminController();
        eController = new ECommerceController();
        sController = new StartController();
        oController = new OrderController();
        tController = new TimeController();
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
            getGameStore().saveInformation();
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
            createAlert("Sorry we could not find your route or controller not specified", Route.ERROR);
            e.printStackTrace();
        }
    }

    private Object setController(Route route) {
        switch (route) {
            case WELCOME:
                return this;
            case ECOMMERCE:
                return eController;
            case ADDCOSTUMER:
                return eController;
            case LOGIN:
                return aController;
            case ADMDASH:
                return aController;
            case SECTION1:
                return sController;
            case SECTION2:
                return oController;
            case SECTION3:
                return tController;
            default:
                return null;
        }
    }

    @FXML
    public void init(ActionEvent event) {
        renderScreen(Route.SECTION1);
        gameStore.initLine();
        sController.initializeStartComerce();
    }

    @FXML
    public void toCommerce(ActionEvent event) {
        renderScreen(Route.ECOMMERCE);
        eController.intializeEComerce();
    }

    @FXML
    public void toLogin(ActionEvent event) {
        renderScreen(Route.LOGIN);
    }

    public void showCostumer() throws IOException {
        oController.initializaOrderClient();
    }

    public void showCostumerTime() throws IOException {
        tController.initTimeUpdate();
    }

    public Stage loadModal(Route route, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(route.getRoute()));
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        try {
            Parent modal = fxmlLoader.load();
            Scene scene = new Scene(modal);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
        } catch (IOException e) {
            createAlert("Rendering fail", Route.WARNING);
        }
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
        gif.setFitWidth(100);
        // ALERT-CONTENT
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.getStylesheets().add(Route.ALERT.getRoute());
        layout.setHeading(new Label("Game Store Alerts"));
        VBox v = new VBox(10, new Label(message), gif);
        v.setAlignment(Pos.CENTER);
        layout.setBody(v);
        // ACTIONS
        JFXButton cancelButton = new JFXButton("OK");
        cancelButton.setButtonType(JFXButton.ButtonType.RAISED);
        cancelButton.setCancelButton(true);
        cancelButton.setStyle(Route.ALERT.getRoute());
        cancelButton.setOnAction(closeEvent -> newAlert.hideWithAnimation());
        layout.setActions(cancelButton);
        // STANGING
        Stage stage = (Stage) newAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:" + Route.ICON.getRoute()));
        newAlert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        newAlert.setContent(layout);
        newAlert.showAndWait();
    }
}