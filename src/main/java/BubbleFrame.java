
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;


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
    private BubbleManager bubbleManager = null;

    private int sceneWidth = 1280, sceneHeight = 960;
    private static double xOffset = 0;
    private static double yOffset = 0;

    public BubbleFrame() {

    }

    public void start(final Stage primaryStage) {
        sceneWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        sceneHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        primaryStage.initStyle(StageStyle.UNDECORATED);
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
        scene.getStylesheets().add(BubbleFrame.class.getResource("bulb.css").toExternalForm());
        scene.getStylesheets().add(BubbleFrame.class.getResource("face.css").toExternalForm());
        scene.getStylesheets().add(BubbleFrame.class.getResource("megaphone.css").toExternalForm());

                scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        stage.show();
    }

    public void addBubble(String author, String text, String shape) {
        PaneControl paneControl = new PaneControl();

        String shapeSet;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.setVgap(5);
        grid.setHgap(5);

        grid.setAlignment(Pos.TOP_LEFT);

        switch (shape) {
            case "bubble":
                grid = setSize(grid, "bubble");
                grid.getStyleClass().add("bubble");
                shapeSet = "bubble";
                break;
            case "empty":
                int number = (int) (Math.random() * 4);
                switch (number) {
                    case 0:
                        grid = setSize(grid, "shield");
                        grid.getStyleClass().add("shield");
                        shapeSet = "shield";
                        break;
                    case 1:
                        grid = setSize(grid, "bulb");
                        grid.getStyleClass().add("bulb");
                        shapeSet = "bulb";
                        break;
                    case 2:
                        grid = setSize(grid, "face");
                        grid.getStyleClass().add("face");
                        shapeSet = "face";
                        break;
                    case 3:
                        grid = setSize(grid, "megaphone");
                        grid.getStyleClass().add("megaphone");
                        shapeSet = "megaphone";
                        break;
                    default:
                        grid = setSize(grid, "shield");
                        grid.getStyleClass().add("shield");
                        shapeSet = "shield";
                        break;
                }
                break;
            default:
                grid = setSize(grid, "shield");
                grid.getStyleClass().add("shield");
                shapeSet = "shield";
                break;

        }
        grid.setLayoutX(getRandomInt((int) (stage.getScene().getWidth())) / 1.1);
        grid.setLayoutY(stage.getScene().getHeight());

        grid.setRotate(randomizer.nextInt(25 + 1 + 25) - 25);
        Label user = new Label();
        Label tweet = new Label();
        user.setText(author);
        user.setMinWidth(130);
        user.setWrapText(true);
        tweet.setMaxWidth(150);
        tweet.setWrapText(true);
        tweet.setTextFill(Color.WHITE);
        tweet.setText(text);

        if (!shape.equals("empty")) {
            user.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: white; " +
                    "-fx-padding: 10px;");
            tweet.setStyle("" +
                    "-fx-background-radius: 10px;" +
                    "-fx-background-color: black; " +
                    "-fx-padding: 10px;");
        }


        switch (shapeSet) {
            case "shield":
                grid.add(user, 0, 1);
                grid.add(tweet, 1, 2);
                break;
            case "bubble":
                grid.add(user, 1, 4);
                grid.add(tweet, 2, 4);
                break;
        }

        paneControl.setUser(user);
        paneControl.setTweet(tweet);
        paneControl.setPanel(grid);
        paneControl.setUserName(user.getText());
        paneControl.setTweetText(tweet.getText());
        bubbles.add(paneControl);
        group.getChildren().add(grid);
    }

    private GridPane setSize(GridPane grid, String shape) {

        switch (shape) {
            case "shield":
                grid.setMinHeight(350);
                grid.setMinWidth(300);
                break;
            case "bubble":
                grid.setMinHeight(300);
                grid.setMinWidth(350);
                break;
            case "bulb":
                grid.setMinHeight(350);
                grid.setMinWidth(300);
                break;
            case "megaphone":
                grid.setMinHeight(300);
                grid.setMinWidth(350);
                break;
            case "face":
                grid.setMinHeight(350);
                grid.setMinWidth(350);
                break;
        }
        return grid;
    }


    public void moveBubbles() {
        for (PaneControl bubble : bubbles) {
            bubble.moveBubble();
        }
    }

    public boolean anyBubbleReachedOtherEnd() {
        if (bubbles.size() != 0) {
            for (PaneControl bubble : bubbles) {
                if (bubble.getPanel().getLayoutY() <= 10) {
                    burstTheBubble(bubble);
                    return true;
                }
            }
        }
        return false;
    }

//    public void enlarge(PaneControl bubble) {
//        if (!bubble.isIncreased()) {
//            double h = bubble.getPanel().getHeight() * 2;
//            double w = bubble.getPanel().getWidth() * 2;
//            bubble.getPanel().setMinWidth(w);
//            bubble.getPanel().setMinHeight(h);
//            bubble.getPanel().setAlignment(Pos.CENTER);
//            Label user = new Label();
//            user.setText(bubble.getUserName());
//            user.setWrapText(true);
//            user.setMaxWidth(100);
//            user.setStyle("" +
//                    "-fx-background-radius: 10px;" +
//                    "-fx-background-color: cornflowerblue; " +
//                    "-fx-font: 15px Tahoma;" +
//                    "-fx-padding: 10px;");
//
//            Label tweet = new Label();
//            tweet.setMaxWidth(400);
//            tweet.setWrapText(true);
//            tweet.setText(bubble.getTweetText());
//            tweet.setStyle("" +
//                    "-fx-background-radius: 10px;" +
//                    "-fx-background-color: coral; " +
//                    "-fx-font: 25px Tahoma;" +
//                    "-fx-padding: 10px;");
//            bubble.getPanel().getChildren().remove(bubble.getUser());
//            bubble.getPanel().getChildren().remove(bubble.getTweet());
//            bubble.getPanel().add(user, 0, 0);
//            bubble.getPanel().add(tweet, 1, 0);
//            bubble.setIncreased(true);
//        }
//    }

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

