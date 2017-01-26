import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleFrameController extends Application {
    private BubbleFrame bubbleFrame = null;
    private BubbleManager bubbleManager = null;

    public BubbleFrameController() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        startBubbleBurst();

    }

    public void startBubbleBurst() {
        if (bubbleFrame == null) {
            bubbleFrame = new BubbleFrame();
            Stage stage = new Stage();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent o) {
                    System.exit(-1);
                }
            });
            bubbleFrame.start(stage);
            bubbleManager = new BubbleManager(this);
            bubbleManager.scheduleBubbleTasks();
        } else {
            bubbleFrame.clearBubbles();
        }

    }

    public BubbleFrame getBubbleFrame() {
        return bubbleFrame;
    }



    public static void main(String[] args) {
        launch(args);
    }

}
