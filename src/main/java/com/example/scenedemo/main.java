package com.example.scenedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    private static Stage primaryStage;
    private static Scene scene1;
    private static Scene scene2;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Load FXML files and create scenes
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("scene1.fxml"));
        Parent root1 = loader1.load();
        scene1 = new Scene(root1);
        scene1.getStylesheets().add(main.class.getResource("styles.css").toExternalForm());

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root2 = loader2.load();
        scene2 = new Scene(root2);
        scene2.getStylesheets().add(main.class.getResource("styles.css").toExternalForm());

        // Set scene 1 as the initial scene
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Number Guessing Game ");
        primaryStage.show();
    }

    public static void setScene2(String name) {
        if (primaryStage != null) {
            try {
                FXMLLoader loader = new FXMLLoader(main.class.getResource("scene2.fxml"));
                Parent root = loader.load();
                scene2.getStylesheets().add(main.class.getResource("styles.css").toExternalForm()); // Add CSS to scene 2
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("Number Guessing Game ");
                Scene2Controller controller = loader.getController();
                controller.setName(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setScene3(){
        try {
            FXMLLoader loader = new FXMLLoader(main.class.getResource("scene3.fxml"));
            Parent root = loader.load();
            Scene scene3 = new Scene(root);
            scene3.getStylesheets().add(main.class.getResource("styles.css").toExternalForm()); // Add CSS to scene 3
            primaryStage.setScene(scene3);
            primaryStage.setTitle("Number Guessing Game ");

            // Call the GameLoop method
            Scene3Controller controller = loader.getController();
            controller.GameLoop();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}