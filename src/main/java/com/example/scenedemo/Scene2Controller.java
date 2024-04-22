package com.example.scenedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Scene2Controller {
    @FXML
    private Label greetingLabel2;

    @FXML
    private Label rules;

    public void setName(String name) {
        //System.out.println("Setting name in Scene 2: " + name);
        greetingLabel2.setText("Hello, " + name + "! \n\n Guess the number, if you can!");
        rules.setText("Here are the rules: \n "+
                "1. You need to guess a number between 1 and 100 \n"+
                "2. You have 3 guesses in total.\n" +
                "3. Hints will guide you along the way.\n\n" +
                "Good luck!");
    }

    @FXML
    public void startGame(ActionEvent event) {
        main.setScene3();
    }


}
