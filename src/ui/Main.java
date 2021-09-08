package ui;

import controller.GameStoreGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GameStore;
import routes.Route;

public class Main extends Application {
    private GameStore gameStore;
    private GameStoreGUI gameStoreGUI;

    public Main() {
        gameStore = new GameStore();
        gameStoreGUI = new GameStoreGUI(gameStore);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(Route.MAINPANE.getRoute()));
        fxmlloader.setController(gameStoreGUI);
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}