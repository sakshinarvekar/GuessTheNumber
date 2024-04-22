package com.example.scenedemo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Scene1Controller {
    @FXML
    private TextField nameField;

    public void goToScene2(ActionEvent actionEvent) {

        String name = nameField.getText();
        main.setScene2(name);
    }
}
