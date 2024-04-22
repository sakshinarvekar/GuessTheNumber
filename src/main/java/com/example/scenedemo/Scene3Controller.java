package com.example.scenedemo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Scene3Controller {
    @FXML
    public TextField guessField;
    @FXML
    public Label hintLabel;
    @FXML
    public Label attemptsLabel;
    @FXML
    public Label randomNo;


    private int numberOfAttempts = 10;
    private int targetNumber = (int) (Math.random() * 100) + 1;

    public void initialize() {
        startNewGame(); // Initialize game state when the scene is loaded
    }

    public void startNewGame() {
        // Generate a new random number
        targetNumber = (int) (Math.random() * 100) + 1;

        // Hiding the random no. label (you may uncomment this for testing purpose)
        //randomNo.setText("Random Number: " + targetNumber);

        // Reset number of attempts
        numberOfAttempts = 10;
        attemptsLabel.setText("Attempts Left: " + numberOfAttempts);

        // Clear hint label and user input
        hintLabel.setText("");
        guessField.clear();

        // Enable guessField
        guessField.setDisable(false);
        if(numberOfAttempts > 0){
            attemptsLabel.setDisable(false);
        }
    }

    public void GameLoop() {
        try {
            String input = guessField.getText().trim();
            if (input.isEmpty()) {
                hintLabel.setText("Please enter a number.");
                return;
            }

            int guess = Integer.parseInt(input);
            if (guess < 1 || guess > 100) {
                hintLabel.setText("Please enter a number between 1 and 100.");
                return;
            }

            if (guess < targetNumber) {
                hintLabel.setText("Try number greater than " + guess);
            } else if (guess > targetNumber) {
                hintLabel.setText("Try number lesser than " + guess);
            } else {
                int attemptsUsed = 10 - numberOfAttempts + 1;
                hintLabel.setText("Well done! You guessed it correctly in "+ attemptsUsed +" attempts");
                guessField.setDisable(true);
                attemptsLabel.setDisable(true);
                return;
            }

            numberOfAttempts--;
            if (numberOfAttempts < 0) {
                numberOfAttempts = 0;
            }
            attemptsLabel.setText("Attempts left: " + numberOfAttempts);

            if (numberOfAttempts == 0) {
                hintLabel.setText("Game over. The correct number was " + targetNumber + ".");
                guessField.setDisable(true); // Disable the text field
                attemptsLabel.setDisable(true);
            }
        } catch (NumberFormatException e) {
            hintLabel.setText("Invalid input. Please enter a valid number.");
        }

    }

    public void playAgain() {
        startNewGame(); // Start a new game
    }

    @FXML
    public void quit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Quit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to quit?");
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 18.0; -fx-text-fill: white;");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Apply CSS styles to buttons
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.lookupButton(buttonTypeYes).setStyle("-fx-text-fill: white;-fx-background-color: #f66614;");
        dialogPane.lookupButton(buttonTypeNo).setStyle("-fx-text-fill: white;-fx-background-color: #f66614;");
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right top, #18005A, #1F0042, #350057);"+
                "-fx-font-family: 'Arial Rounded MT Bold';" +
                "-fx-font-size: 18.0;"+
                "-fx-text-fill: white;");
        // Hover effect for buttons
        dialogPane.lookupButton(buttonTypeYes).setOnMouseEntered(e -> ((Button) dialogPane.lookupButton(buttonTypeYes)).setStyle("-fx-background-color: derive(#f66614, 20%); -fx-text-fill: white;"));
        dialogPane.lookupButton(buttonTypeYes).setOnMouseExited(e -> ((Button) dialogPane.lookupButton(buttonTypeYes)).setStyle("-fx-text-fill: white;-fx-background-color: #f66614;"));
        dialogPane.lookupButton(buttonTypeNo).setOnMouseEntered(e -> ((Button) dialogPane.lookupButton(buttonTypeNo)).setStyle("-fx-background-color: derive(#f66614, 20%); -fx-text-fill: white;"));
        dialogPane.lookupButton(buttonTypeNo).setOnMouseExited(e -> ((Button) dialogPane.lookupButton(buttonTypeNo)).setStyle("-fx-text-fill: white;-fx-background-color: #f66614;"));


        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                Platform.exit();
            }
            else{
                playAgain();
            }
        });

    }
}
