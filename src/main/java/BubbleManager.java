import twitter4j.*;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/26/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleManager extends Timer {
    private BubbleFrameController bubbleFrameController = null;
    private BubbleFrame bubbleFrame = null;
    private Random randomizer = new Random();
    List<Status> tweets;

    public BubbleManager(BubbleFrameController bubbleFrameController) {
        this.bubbleFrameController = bubbleFrameController;
        this.bubbleFrame = bubbleFrameController.getBubbleFrame();
    }

    public void scheduleBubbleTasks() {
        schedule(twitterSearch(), Calendar.getInstance().getTime(), 40000);

        schedule(getMoveBubblesTask(), Calendar.getInstance().getTime(), 30);

        schedule(getAddBubblesTask(), Calendar.getInstance().getTime(), 3000);






    }

    private TimerTask getMoveBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                    if (!bubbleFrame.anyBubbleReachedOtherEnd()) {
                        bubbleFrame.removeBubbles();
                        bubbleFrame.moveBubbles();
                    }
            }
        };
    }

    private TimerTask getAddBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                    if (bubbleFrame.getRandomInt(100) > 50) {
                        Status tweet;
                        synchronized(this) {
                            int size = tweets.size();

                            tweet = tweets.get(randomizer.nextInt(size));
                        }
                        bubbleFrame.addBubble("@" + tweet.getUser().getScreenName(),tweet.getText());

                    }
            }
        };
    }

     private TimerTask twitterSearch(){
         return new TimerTask() {
             @Override
             public void run() {
                 Twitter twitter = new TwitterFactory().getInstance();
                 try {
                     synchronized(this) {
                         Query query = new Query("trump");
                         QueryResult result;
                         result = twitter.search(query);
                         tweets = result.getTweets();
                     }

                 } catch (TwitterException te) {
                     te.printStackTrace();
                 }
             }
         };
     }

}
