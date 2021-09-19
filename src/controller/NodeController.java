package controller;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Costumer;
import model.Game;

public class NodeController {
    @FXML
    private Label costumerName;

    @FXML
    private Label costumerId;

    @FXML
    private Label costumerTime;

    @FXML
    private JFXTextArea costumerCode;

    @FXML
    private Label slot;

    @FXML
    private Label game;

    public NodeController(){
        
    }

    public void getCostumer(Costumer c){
        costumerCode.setText(c.getGames());
        costumerId.setText(String.valueOf(c.getId()));
        costumerName.setText(c.getName());
        costumerTime.setText(c.getTimeInShop() + " MIN");
    }

    public void getRack(String s, Game g){
        slot.setText(s);
        game.setText("Cod. "+ g.getCode() + "  $ " + g.getPrice());
    }
}
