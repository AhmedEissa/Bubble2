import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleBurstHandler implements EventHandler<MouseEvent> {
    private BubbleFrame bubbleFrame = null;
    private PaneControl bubble = null;

    public BubbleBurstHandler(BubbleFrame bubbleFrame, PaneControl bubble) {
        this.bubbleFrame = bubbleFrame;
        this.bubble = bubble;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 0) {
            //bubbleFrame.enlarge(bubble);
            //bubbleFrame.burstTheBubble(bubble);
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            bubble.getPanel().setCursor(Cursor.CLOSED_HAND);
        }
    }
}

