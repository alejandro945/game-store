package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;

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
            initComboGameBox();
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
            modal.show();
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        GameStoreGUI.getInstance().renderScreen(Route.WELCOME);
    }

    private void getData() {
        // Init Cashiers
        lblCashier.setText(String.valueOf(GameStoreGUI.getInstance().getGameStore().getCashiers()));
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
                || title.getText().equals("")) {
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
    }

    @FXML
    public void saveGame(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
        if (validateFields()) {
            try {
                GameStoreGUI.getInstance().getGameStore().addGame(title.getText(), review.getText(),
                        Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()));
                GameStoreGUI.getInstance().createAlert("Your game has been added succesfully", Route.SUCCESS);
                trimForm();

                getData();
                cancelModal(event);
            } catch (NumberFormatException e) {
                GameStoreGUI.getInstance().createAlert("You can not write letters in price or amount!", Route.ERROR);
            }
        } else {
            GameStoreGUI.getInstance().createAlert("Some empty Fields!", Route.WARNING);
        }
    }

    @FXML
    public void editGame(ActionEvent event) {
        selectedG.setGameName(title.getText());
        selectedG.setReview(review.getText());
        selectedG.setAmount(Integer.parseInt(amount.getText()));
        selectedG.setPrice(Integer.parseInt(price.getText()));
        GameStoreGUI.getInstance().createAlert("Game edited succesfully", Route.SUCCESS);
        getData();
        cancelModal(event);
    }

    // ---------------------------------------CREATE-SHELVE----------------------------------------------

    private List<Game> prelistGames = new ArrayList<>();

    @FXML
    private TableView<Shelve> tbShelves;

    @FXML
    private TableColumn<?, ?> tblIdShelves;

    @FXML
    private TableColumn<?, ?> tblGamesShelves;

    @FXML
    private TableColumn<?, ?> colActionsShelves;

    @FXML
    private Label lblShelve;

    @FXML
    private JFXTextField shelveName;

    @FXML
    private JFXComboBox<Game> chooseGame;

    @FXML
    private JFXTextArea txtAreaGames;

    @FXML
    private JFXButton btnClear;

    public boolean shelvesValidation(String name) {
        boolean complete = true;
        if (name.equals("") || chooseGame.getSelectionModel().getSelectedItem() == null) {
            complete = false;
        }
        return complete;
    }

    public void trimShelve() {
        shelveName.setText("");
        chooseGame.setValue(null);
    }

    public void initBtnClear() {
        if (txtAreaGames.getText() != "") {
            btnClear.setDisable(false);
        }
    }

    public void initComboGameBox() {
        chooseGame.getItems().addAll(GameStoreGUI.getInstance().getGameStore().getGames());
    }

    @FXML
    public void comboGame(ActionEvent event) {
        initBtnClear();
        boolean exist = GameStoreGUI.getInstance().getGameStore().addGameToShelve(chooseGame.getValue(), prelistGames);
        txtAreaGames.setText(Arrays.toString(prelistGames.toArray()));
        if (!exist) {
            GameStoreGUI.getInstance().createAlert("You can not add the same game at same shelve", Route.WARNING);
        }

    }

    @FXML
    public void clear(ActionEvent event) {
        if (txtAreaGames.getText().equals("")) {
            GameStoreGUI.getInstance().createAlert("The list is empty", Route.ALERT);
        } else {
            txtAreaGames.setText("");
            prelistGames.clear();
            btnClear.setDisable(true);
        }

    }

    @FXML
    public void cancelModalShelve(ActionEvent event) {
        modal.close();
        modal = null;
    }

    @FXML
    public void saveShelve(ActionEvent event) {
        boolean validateShelves = shelvesValidation(shelveName.getText());
        if (!validateShelves) {
            GameStoreGUI.getInstance().createAlert("Please, complete all the fields", Route.WARNING);
        }
    }

    @FXML
    public void editShelve(ActionEvent event) {

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
            lblCashier.setText(String.valueOf(GameStoreGUI.getInstance().getGameStore().getCashiers()));
        } catch (NumberFormatException e) {
            GameStoreGUI.getInstance().createAlert("Required numeric field", Route.ERROR);
        }

    }

    // ----------------------------------------GENERICS------------------------------------------

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
                            GameStoreGUI.getInstance().getGameStore().getGames().remove(selectedG);
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
        edit.setVisible(true);
        save.setVisible(false);
    }

}
