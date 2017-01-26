
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Bubble Frame
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/23/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleFrame {
    private Group group = null;
    private List<PaneControl> bubbles = new ArrayList<>();
    private Random randomizer = new Random();
    private Stage stage = null;

    private int sceneWidth = 1280, sceneHeight = 960;

    public BubbleFrame() {

    }

    public void start(Stage primaryStage) {
        sceneWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        sceneHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        stage = primaryStage;
        stage.setTitle("Bubble Burst");
        group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.BLACK);
        stage.setOpacity(0.7);
        stage.setScene(scene);
        stage.setMinWidth(sceneWidth);
        stage.setMinHeight(sceneHeight);
        stage.setMaxWidth(sceneWidth);
        stage.setMaxHeight(sceneHeight);
        scene.setCursor(Cursor.HAND);
        scene.getStylesheets().add(BubbleFrame.class.getResource("shield.css").toExternalForm());
        scene.getStylesheets().add(BubbleFrame.class.getResource("bubble.css").toExternalForm());
        stage.show();
    }

    public void addBubble(String author,String text) {
        PaneControl paneControl = new PaneControl();

        GridPane grid = new GridPane();
        BubbleBurstHandler bubbleBurstHandler = new BubbleBurstHandler(this, paneControl);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinHeight(450);
        grid.setMinWidth(300);
        grid.setVgap(5);
        grid.setHgap(5);

        grid.setAlignment(Pos.TOP_LEFT);
        if(text.toLowerCase().contains("yes")){
            grid.getStyleClass().add("bubble");
        }else if(text.toLowerCase().contains("no")){
            grid.getStyleClass().add("shield");
        }else{
            grid.getStyleClass().add("bubble");
        }
        grid.setLayoutX(getRandomInt((int) (stage.getScene().getWidth()))/1.1);
        grid.setLayoutY(stage.getScene().getHeight());
        grid.setOnMouseClicked(bubbleBurstHandler);
        grid.setOnMouseEntered(bubbleBurstHandler);

        grid.setRotate(randomizer.nextInt(25+1+25)-25);

        Label user = new Label();
        user.setText(author);
        user.setWrapText(true);
        user.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: cornflowerblue; " +
                "-fx-padding: 10px;");

        Label tweet = new Label();
        tweet.setMaxWidth(150);
        tweet.setWrapText(true);
        tweet.setText(text);
        tweet.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: coral; " +
                "-fx-padding: 10px;");

        grid.add(user,0,0);
        grid.add(tweet,1,0);
        paneControl.setUser(user);
        paneControl.setTweet(tweet);
        paneControl.setPanel(grid);
        paneControl.setUserName(user.getText());
        paneControl.setTweetText(tweet.getText());
        bubbles.add(paneControl);
        group.getChildren().add(grid);
    }

    public void moveBubbles() {
        for (PaneControl bubble : bubbles) {
            bubble.getPanel().setLayoutY(bubble.getPanel().getLayoutY() - 1);
        }
    }


    public void removeBubbles() {
        List<GridPane> bubblesToBeRemoved = new ArrayList<>();
        for (PaneControl bubble : bubbles) {
            if (bubble.getPanel().getLayoutY() <= 10) {
                group.getChildren().remove(bubble.getPanel());
            }

        }
        bubbles.removeAll(bubblesToBeRemoved);
    }

    public boolean anyBubbleReachedOtherEnd() {
        for (PaneControl bubble : bubbles) {
            if (bubble.getPanel().getLayoutY() <= 10) {
                burstTheBubble(bubble);
                return true;
            }
        }
        return false;
    }

    public void enlarge(PaneControl bubble) {
        if(!bubble.isIncreased()) {
            double h = bubble.getPanel().getHeight() * 2;
            double w = bubble.getPanel().getWidth() * 2;
            bubble.getPanel().setMinWidth(w);
            bubble.getPanel().setMinHeight(h);
            bubble.getPanel().setAlignment(Pos.CENTER);
            Label user = new Label();
            user.setText(bubble.getUserName());
            user.setWrapText(true);
            user.setMaxWidth(100);
            user.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: cornflowerblue; " +
                    "-fx-font: 15px Tahoma;"+
                    "-fx-padding: 10px;");

            Label tweet = new Label();
            tweet.setMaxWidth(400);
            tweet.setWrapText(true);
            tweet.setText(bubble.getTweetText());
            tweet.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: coral; " +
                    "-fx-font: 25px Tahoma;"+
                    "-fx-padding: 10px;");
            bubble.getPanel().getChildren().remove(bubble.getUser());
            bubble.getPanel().getChildren().remove(bubble.getTweet());
            bubble.getPanel().add(user,0,0);
            bubble.getPanel().add(tweet,1,0);
            bubble.setIncreased(true);
        }
    }

    public void burstTheBubble(PaneControl bubble) {
        group.getChildren().remove(bubble.getPanel());
        bubbles.remove(bubble);
    }

    public int getRandomInt(int range) {
        int randomInt = randomizer.nextInt() % range;
        if (randomInt < 0) {
            randomInt = -randomInt;
        }
        return randomInt;
    }


    public void clearBubbles() {
        group.getChildren().removeAll(bubbles);
        bubbles.clear();
    }

}
