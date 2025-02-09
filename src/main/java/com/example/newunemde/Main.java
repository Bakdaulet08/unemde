package com.example.newunemde;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {

    @Override


    public void start(Stage stage) throws IOException {

        Image logo = new Image(new FileInputStream("src/main/resources/images/logo3.png"));
        try{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Unemde");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();}

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}