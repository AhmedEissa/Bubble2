import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by Matthew on 14/01/2017.
 */
public class PaneControl {

    private GridPane panel;
    private boolean increased = false;
    private Label user;
    private Label tweet;
    private String userName;
    private String tweetText;

    public PaneControl() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Label getUser() {
        return user;
    }

    public void setUser(Label user) {
        this.user = user;
    }

    public Label getTweet() {
        return tweet;
    }

    public void setTweet(Label tweet) {
        this.tweet = tweet;
    }

    public void setPanel(GridPane panel) {
        this.panel = panel;
    }

    public void setIncreased(boolean increased) {
        this.increased = increased;
    }

    public GridPane getPanel() {
        return panel;
    }

    public boolean isIncreased() {
        return increased;
    }
}
