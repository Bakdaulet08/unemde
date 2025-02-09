package com.example.newunemde;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class loadingController {
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    MediaView mediaView;

    @FXML
    private Circle firstLight, secondLight, thirdLight;


    public void initialize() {

        file = new File("src/main/resources/images/0009.png0000-0065.mp4");
        media = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setCycleCount(3);
        mediaPlayer.play();
        FadeTransition fd1 = createFadeTransition(firstLight, 0);
        fd1.play();
        FadeTransition fd2 = createFadeTransition(secondLight, 500);
        fd2.play();
        FadeTransition fd3 = createFadeTransition(thirdLight, 1000);



        fd3.play();
        fd3.setOnFinished(e -> {
            Stage stage = (Stage) mediaView.getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene); // Устанавливаем новую сцену
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });











    }






    private FadeTransition createFadeTransition(Circle circle, int delay) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), circle);
        fadeTransition.setFromValue(1);  // начальная яркость
        fadeTransition.setToValue(0);    // конечная яркость
        fadeTransition.setCycleCount(8); // повторять 3 раза
        fadeTransition.setAutoReverse(true); // обратное движение (появление и исчезновение)
        fadeTransition.setDelay(Duration.millis(delay)); // задержка перед запуском анимации
        return fadeTransition;
    }
}
