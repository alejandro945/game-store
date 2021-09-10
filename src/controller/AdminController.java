package controller;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import routes.Route;

public class AdminController {
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    @FXML
    public void signIn(ActionEvent event) {

    }
}
