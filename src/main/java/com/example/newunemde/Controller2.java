package com.example.newunemde;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class Controller2 {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField enterPassToSign;
    @FXML
    TextField enterEmailToSign;
    @FXML
    TextField enterLastName;
    @FXML
    TextField enterFirstname;

    @FXML Label smthIsEmpty;


    @FXML
    private ImageView logo3;

    public void initialize() {

    }





    @FXML
    private void signUpNewUser(ActionEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = enterFirstname.getText().trim();
        String lastName = enterLastName.getText().trim();
        String userEmail = enterEmailToSign.getText().trim();
        String userPassword = enterPassToSign.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            System.out.println("Все поля должны быть заполнены.");
            FadeTransition fd1 = new FadeTransition(Duration.millis(3000), smthIsEmpty);
            fd1.setFromValue(1.0);
            fd1.setToValue(0.0);
            fd1.play();
            return;
        }

        User user = new User(firstName, lastName, userEmail, userPassword);

        try {
            dbHandler.signUpUser(user);
            System.out.println("Регистрация прошла успешно!");

            Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ошибка регистрации: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Класс не найден: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ошибка загрузки страницы логина: " + ex.getMessage());
        }
    }


}
