package com.example.newunemde;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Controller3 {

    @FXML
    private Button applyIncome, applyForDay;

    @FXML
    private TextField enteredOutcome1, enteredOutcome2, enterIncome, dividingForDay;

    @FXML
    Label Balance, dailyBalance, Balance2, resForDay;

    @FXML
    private Button mainButton;
    @FXML
    private Button planButton;
    @FXML
    private Button informButton;

    @FXML
    private ImageView logo2;
    private Stage stage;
    private Scene scene;
    @FXML
    AnchorPane planPane;








    public void initialize() {
    }

    public void decreaseSum() {
        if (enteredOutcome1 != null && !enteredOutcome1.getText().equals("")) {

            int currentBalance = Integer.valueOf(obrString(dailyBalance.getText()));
            int outcome = Integer.valueOf(enteredOutcome1.getText());
            String sum = pastePoint(String.valueOf(currentBalance - outcome));
            if (sum.charAt(0) == '-'){
                sum = sum.charAt(0) + sum.substring(2);
            }
            dailyBalance.setText(sum);

        } else {
            System.out.println("TextField is null or empty");
        }
        enteredOutcome1.setText("");
    }
    public void decreaseSum2() {
        if (enteredOutcome2 != null && !enteredOutcome2.getText().equals("")) {

            String  reBalance = Balance.getText();
            String normBalance = "";
            for (int i = 0; i < reBalance.length(); i++) {
                if (reBalance.charAt(i) != ','){
                    normBalance += reBalance.charAt(i);
                }
            }
            int currentBalance = Integer.valueOf(normBalance);
            int outcome = Integer.valueOf(enteredOutcome2.getText());


            Balance.setText(pastePoint(String.valueOf(currentBalance - outcome)));
            enteredOutcome2.setText("");

            
        } else {
            System.out.println("TextField is null or empty");
        }
    }


    public void dividingForMonth(){
        if (dividingForDay != null && !dividingForDay.getText().equals("")){
            int a = Integer.valueOf(dividingForDay.getText());
            resForDay.setText(pastePoint(String.valueOf(a * 30)));
            dividingForDay.setText("");

        }

    }

    public void enterIncome(){
        if (enterIncome != null && !enterIncome.getText().equals("")){
            String bal = Balance2.getText();
            String newBal = "";
            for (int i = 0; i < bal.length(); i++) {
                if (bal.charAt(i) != ','){
                    newBal += bal.charAt(i);
                }
            }
            int newBalyk = Integer.parseInt(newBal);
            int income = Integer.parseInt(enterIncome.getText());
            Balance2.setText(pastePoint(String.valueOf(newBalyk + income)));
            enterIncome.setText("");

        }
    }
    public void switchToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
    }

    public void switchToPlanPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("planPage.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
    }

    public void switchToInformPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("informPage.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
    }

    public void hoverAffect(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #c4712e; -fx-text-fill: white;");
    }

    public void noHoverAffect(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color:  #e4d41f; -fx-text-fill: white;");
    }
    public String pastePoint(String balance){
        String newBal = balance;
        boolean flag = false;
        if (balance.length() % 3 == 0){
            flag = true;
        }
        String revbal = "";
        for (int i = newBal.length()-1; i >-1 ; i--) {
            revbal += newBal.charAt(i);
        }
        newBal = "";
        int cnt = 0;
        for (int i = 0; i < revbal.length(); i++) {
            cnt++;
            newBal += revbal.charAt(i);
            if (cnt == 3){
                newBal += ",";
                cnt = 0;
            }

        }
        revbal = "";
        for (int i = newBal.length()-1; i >-1 ; i--) {
            revbal += newBal.charAt(i);
        }
        if (flag){
            return revbal.substring(1);
        }
        return revbal;
    }
    public String obrString(String str){
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ','){
                newStr += str.charAt(i);
            }
        }
        return newStr;
    }

    @FXML
    private Pane paneWish;
    private boolean flag = false;
    @FXML
    void addNewWish(ActionEvent event) {
        flag = !flag;
        paneWish.setVisible(flag);
    }
    @FXML
    private Button addButton;

    @FXML
    private TextField enterWish;
    private int xForRec = 318;
    private int yForRec = 645;

    private int xForLab = 323;
    private int yForLab = 652;
    public void addWish() {
        if (enterWish != null && !enterWish.getText().equals("")) {
            // Создаем прямоугольник для пожелания
            Rectangle rec = new Rectangle(253, 46);
            rec.setStyle(
                    "-fx-fill: #e5e8ec; " +            // Цвет заливки
                            "-fx-stroke: black; " +         // Цвет обводки
                            "-fx-stroke-width: 1; "    // Толщина обводки
                             // Эффект тени
            );

            Label lab = new Label(enterWish.getText());
            lab.setStyle("fx-font-size:20");
            rec.setLayoutX(xForRec);
            rec.setLayoutY(yForRec);
            yForRec += 44;

            lab.setLayoutX(xForLab);
            lab.setLayoutY(yForLab);
            yForLab += 44;
            paneWish.setTranslateZ(10);
            rec.setTranslateZ(-10);
            lab.setTranslateZ(-10);

            planPane.getChildren().add(rec);
            planPane.getChildren().add(lab);
            enterWish.setText("");
        }
    }

}
