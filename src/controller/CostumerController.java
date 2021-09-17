package controller;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Costumer;

public class CostumerController {
    @FXML
    private Label costumerName;

    @FXML
    private Label costumerId;

    @FXML
    private Label costumerTime;

    @FXML
    private JFXTextArea costumerCode;

    public CostumerController(){
        
    }

    public void getData(Costumer c){
        costumerCode.setText(c.getCode());
        costumerId.setText(String.valueOf(c.getId()));
        costumerName.setText(c.getName());
        costumerTime.setText(c.getTimeInShop() + " MIN");
    }
}
