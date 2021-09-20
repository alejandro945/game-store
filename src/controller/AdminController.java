package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import model.Costumer;
import model.Game;
import model.GameStore;
import model.Shelve;
import routes.Route;

public class AdminController {

    private Game selectedG;
    private Shelve selectedS;
    private Stage modal;

    // -----------------------------------------------ADMIN-DASH-----------------------------

    @FXML
    public void cancelModal(ActionEvent event) {
        modal.close();
        modal = null;
    }

    public void showModal() throws IOException {
        modal = GameStoreGUI.getInstance().loadModal(Route.GAMEMODAL, this);
        modal.show();
    }

    @FXML
    public void createShelve(ActionEvent event) {
        if (modal == null) {
            modal = GameStoreGUI.getInstance().loadModal(Route.SHELVEMODAL, this);
            modal.show();
        }
    }

    @FXML
    public void createGame(ActionEvent event) {
        if (modal == null) {
            modal = GameStoreGUI.getInstance().loadModal(Route.GAMEMODAL, this);
            code.setText(String.valueOf(GameStoreGUI.getInstance().getGameStore().getGames().size() + 1));
            edit.setVisible(false);
            save.setVisible(true);
            initComboShelves();
            modal.show();
        }
    }

    private void initComboShelves() {
        GameStore g = GameStoreGUI.getInstance().getGameStore();
        try {
            List<String> shelvesNames = new ArrayList<>();
            for (Shelve s : g.getShelves()) {
                shelvesNames.add(s.getNameShelve());
            }
            ObservableList<String> obs = FXCollections.observableArrayList(shelvesNames);
            comboAddGameToShelve.setItems(obs);
        } catch (NullPointerException e) {
            GameStoreGUI.getInstance().createAlert("Sorry we do not have shelves yet", Route.WARNING);
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    private void getData() {
        // Init Cashiers
        lblCashier.setText(String.valueOf(GameStoreGUI.getInstance().getGameStore().getLengthCashiers()));
        // Init Game Table
        ObservableList<Game> gameList = FXCollections
                .observableArrayList(GameStoreGUI.getInstance().getGameStore().getGames());
        tblIdGames.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblNameGames.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        tblReviewGames.setCellValueFactory(new PropertyValueFactory<>("review"));
        tblPriceGames.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblAmountGames.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbGames.setItems(gameList);
        // Init Costumers Table
        ObservableList<Costumer> costumerList = FXCollections
                .observableArrayList(GameStoreGUI.getInstance().getGameStore().getCostumers());
        tbCostumers.setItems(costumerList);
        tblIdCostumer.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNameCostumers.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblGamesCostumers.setCellValueFactory(new PropertyValueFactory<>("games"));
        renderGameActions();
        // Init Shelves Table
        ObservableList<Shelve> shelveList = FXCollections
                .observableArrayList(GameStoreGUI.getInstance().getGameStore().getShelves());
        tbShelves.setItems(shelveList);
        tblInameShelves.setCellValueFactory(new PropertyValueFactory<>("nameShelve"));
        tblRacks.setCellValueFactory(new PropertyValueFactory<>("size"));
        renderGameActions();
        renderShelveActions();
    }

    // ---------------------------------------SING-IN------------------------------------------
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField password;

    @FXML
    public void signIn(ActionEvent event) {
        if (userName.getText().equalsIgnoreCase("root") && password.getText().equalsIgnoreCase("root")) {
            GameStoreGUI.getInstance().renderScreen(Route.ADMDASH);
            getData();
        } else {
            GameStoreGUI.getInstance().createAlert("You aren't admin", Route.ERROR);
        }
    }

    // --------------------------------------CREATE-GAMES--------------------------------------------

    @FXML
    private TableView<Game> tbGames;

    @FXML
    private TableColumn<?, ?> tblIdGames;

    @FXML
    private TableColumn<?, ?> tblNameGames;

    @FXML
    private TableColumn<?, ?> tblAmountGames;

    @FXML
    private TableColumn<?, ?> tblPriceGames;

    @FXML
    private TableColumn<?, ?> tblReviewGames;

    @FXML
    private JFXComboBox<String> comboAddGameToShelve;

    @FXML
    private TableColumn<Game, String> colActions;

    @FXML
    private Label modalGameName;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXTextArea review;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button save;

    public Boolean validateFields() {
        if (price.getText().equals("") || amount.getText().equals("") || review.getText().equals("")
                || title.getText().equals("") || comboAddGameToShelve.getSelectionModel().getSelectedItem() == null) {
            return false;
        } else {
            return true;
        }

    }

    public void trimForm() {
        price.setText("");
        amount.setText("");
        review.setText("");
        title.setText("");
        comboAddGameToShelve.setValue(null);
    }

    @FXML
    public void saveGame(ActionEvent event) {
        if (validateFields()) {
            try {
                String msg = GameStoreGUI.getInstance().getGameStore().addGame(0,title.getText(), review.getText(),
                        Integer.parseInt(price.getText()), comboAddGameToShelve.getValue(),
                        Integer.parseInt(amount.getText()));
                if (msg.equals("Game added succesfully")) {
                    GameStoreGUI.getInstance().createAlert(msg, Route.SUCCESS);
                } else {
                    GameStoreGUI.getInstance().createAlert(msg, Route.ERROR);
                }
                trimForm();
                getData();
                cancelModal(event);
            } catch (NumberFormatException e) {
                GameStoreGUI.getInstance().createAlert("You can not write letters in price or amount!", Route.ERROR);
            } catch (Exception e) {
                GameStoreGUI.getInstance().createAlert("Contact Alejandro Varela", Route.ERROR);
            }
        } else {
            GameStoreGUI.getInstance().createAlert("Some empty Fields!", Route.WARNING);
        }
    }

    @FXML
    public void editGame(ActionEvent event) {
        GameStoreGUI.getInstance().getGameStore().removeGame(selectedG);
        String msg = GameStoreGUI.getInstance().getGameStore().addGame(selectedG.getCode(), title.getText(), review.getText(),
                Integer.parseInt(price.getText()), comboAddGameToShelve.getValue(), Integer.parseInt(amount.getText()));
        if (msg.equals("Game added succesfully")) {
            GameStoreGUI.getInstance().createAlert("Game edited succesfully", Route.SUCCESS);
        } else {
            GameStoreGUI.getInstance().createAlert(msg, Route.ERROR);
        }
        trimForm();
        getData();
        cancelModal(event);
    }

    // ---------------------------------------CREATE-SHELVE----------------------------------------------

    @FXML
    private TableView<Shelve> tbShelves;

    @FXML
    private TableColumn<?, ?> tblInameShelves;

    @FXML
    private TableColumn<?, ?> tblRacks;

    @FXML
    private TableColumn<Shelve, String> colActionsShelves;

    @FXML
    private Label lblShelve;

    @FXML
    private JFXTextField shelveName;

    @FXML
    private JFXTextField rowsShelve;

    public boolean shelvesValidation(String name, int amountRows) {
        boolean empty = false;
        if (name.equals("") || amountRows == 0) {
            empty = true;
        }
        return empty;
    }

    public void trimShelve() {
        shelveName.setText("");
        rowsShelve.setText("");
    }

    @FXML
    public void cancelModalShelve(ActionEvent event) {
        modal.close();
        modal = null;
    }

    @FXML
    public void saveShelve(ActionEvent event) {
        try {
            boolean empty = shelvesValidation(shelveName.getText(), Integer.parseInt(rowsShelve.getText()));
            if (empty) {
                GameStoreGUI.getInstance().createAlert("Please, complete all the fields", Route.WARNING);
            } else {
                String msg = GameStoreGUI.getInstance().getGameStore().addShelve(shelveName.getText(),
                        Integer.parseInt(rowsShelve.getText()));
                GameStoreGUI.getInstance().createAlert(msg, Route.WARNING);
                trimShelve();
                getData();
                cancelModalShelve(event);
            }
        } catch (NumberFormatException e) {
            GameStoreGUI.getInstance().createAlert("Amount of rows in shelve must be a number", Route.ERROR);
        }
    }

    // ----------------------------------VIEW-COSTUMERS----------------------------------------

    @FXML
    private TableView<Costumer> tbCostumers;

    @FXML
    private TableColumn<Costumer, Integer> tblIdCostumer;

    @FXML
    private TableColumn<Costumer, String> tblNameCostumers;

    @FXML
    private TableColumn<Costumer, String> tblGamesCostumers;

    // ----------------------------------------CASHIERS----------------------------------------------

    @FXML
    private JFXTextField txtCashier;

    @FXML
    private JFXTextField lblCashier;

    @FXML
    public void editCashier(ActionEvent event) {
        try {
            GameStoreGUI.getInstance().getGameStore().initCashiers(Integer.parseInt(txtCashier.getText()));
            GameStoreGUI.getInstance().createAlert("Journal Cashiers edited ", Route.SUCCESS);
            txtCashier.setText("");
            lblCashier.setText(String.valueOf(GameStoreGUI.getInstance().getGameStore().getLengthCashiers()));
        } catch (NumberFormatException e) {
            GameStoreGUI.getInstance().createAlert("Required numeric field", Route.ERROR);
        }

    }

    // ----------------------------------------ACTIONS------------------------------------------

    private void renderGameActions() {
        Callback<TableColumn<Game, String>, TableCell<Game, String>> cellFact = (TableColumn<Game, String> param) -> {
            final TableCell<Game, String> cell = new TableCell<Game, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button delete = new Button("Delete");
                        delete.setId("delete");
                        Button edit = new Button("Edit");
                        edit.setId("edit");
                        delete.getStylesheets().add(Route.TABLE.getRoute());
                        edit.getStylesheets().add(Route.TABLE.getRoute());
                        delete.setOnAction((ActionEvent event) -> {
                            selectedG = (Game) getTableRow().getItem();
                            GameStoreGUI.getInstance().getGameStore().removeGame(selectedG);
                            GameStoreGUI.getInstance().createAlert("The game was removed succesfully!", Route.SUCCESS);
                            getData();
                        });
                        edit.setOnAction((ActionEvent event) -> {
                            if (modal == null) {
                                selectedG = (Game) getTableRow().getItem();
                                try {
                                    showModal();
                                } catch (IOException e) {
                                    GameStoreGUI.getInstance().createAlert("Edition Priblems, Close any modal opened",
                                            Route.ERROR);
                                }
                                modalGameName.setText("Edit Game");
                                prepareGameEdition(selectedG);
                            }
                        });
                        HBox managebtn = new HBox(edit, delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));
                        HBox.setMargin(edit, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        colActions.setCellFactory(cellFact);
    }

    public void prepareGameEdition(Game selected) {
        code.setText(String.valueOf(selected.getCode()));
        title.setText(selected.getGameName());
        price.setText(String.valueOf(selected.getPrice()));
        review.setText(selected.getReview());
        amount.setText(String.valueOf(selected.getAmount()));
        initComboShelves();
        comboAddGameToShelve.setValue(selected.getShelveName());
        ;
        edit.setVisible(true);
        save.setVisible(false);
    }

    private void renderShelveActions() {
        Callback<TableColumn<Shelve, String>, TableCell<Shelve, String>> cellFact = (
                TableColumn<Shelve, String> param) -> {
            final TableCell<Shelve, String> cell = new TableCell<Shelve, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button delete = new Button("Delete");
                        delete.setId("delete");
                        delete.getStylesheets().add(Route.TABLE.getRoute());
                        delete.setOnAction((ActionEvent event) -> {
                            selectedS = (Shelve) getTableRow().getItem();
                            boolean render = GameStoreGUI.getInstance().getGameStore().removeShelve(selectedS);
                            if (render) {
                                GameStoreGUI.getInstance().createAlert("The shelve was removed succesfully!",
                                        Route.SUCCESS);
                            } else {
                                GameStoreGUI.getInstance().createAlert("The shelve has games or references Sorry",
                                        Route.ERROR);
                            }
                            getData();
                        });
                        HBox managebtn = new HBox(delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        colActionsShelves.setCellFactory(cellFact);
    }

}
