package com.example.newunemde;

import animations.Shake;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Controller1 {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField enterEmailToSign;
    @FXML
    private TextField enterPassToSign;
    @FXML
    private TextField enterLogin;
    @FXML
    private TextField enterPassword;
    @FXML
    private Label wrongPass;

    @FXML
    private ImageView logo1;

    @FXML
    private Button toMain;

    public void initialize() {

        toMain.setOnAction(event -> {
            String loginText = enterLogin.getText().trim();
            String loginPass = enterPassword.getText().trim();

            if (!loginText.equals("") && !loginPass.equals("")) {
                try {
                    loginUser(loginText, loginPass);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else System.out.println("Login or Password is empty");

        });
    }

    private void loginUser(String loginText, String loginPass) throws SQLException, IOException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPass);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while (result.next()) {
            counter++;
        }
        if (counter >= 1) {
            System.out.println("Success");
            switchToSceneMain();



        } else {
            Shake userLoginAnim = new Shake(enterLogin);
            Shake userPassAnim = new Shake(enterPassword);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();

        }
    }

    public void openNewScene(String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void switchToSceneSignUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
    }
    public void switchToSceneMain() throws IOException {
        // Загружаем новый FXML файл
        Parent root = FXMLLoader.load(getClass().getResource("loadingScene.fxml"));

        // Получаем Stage из текущей сцены
        Stage stage = (Stage) enterLogin.getScene().getWindow();  // Получаем Stage через один из элементов на сцене

        // Создаем новую сцену
        Scene scene = new Scene(root);

        // Устанавливаем сцену на Stage
        stage.setScene(scene);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}