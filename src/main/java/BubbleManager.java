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
    ArrayList<Status> tweets;

    public BubbleManager(BubbleFrameController bubbleFrameController) {
        this.bubbleFrameController = bubbleFrameController;
        this.bubbleFrame = bubbleFrameController.getBubbleFrame();
    }


    public void scheduleBubbleTasks() {
        schedule(twitterSearch(), Calendar.getInstance().getTime(), 40000);

        schedule(getMoveBubblesTask(), Calendar.getInstance().getTime(), 50);

        schedule(getAddBubblesTask(), Calendar.getInstance().getTime(), 6000);


    }

    private TimerTask getMoveBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                if (!bubbleFrame.anyBubbleReachedOtherEnd()) {
                    bubbleFrame.moveBubbles();
                }
            }
        };
    }

    private TimerTask getAddBubblesTask() {
        return new UIUpdateTimerTask() {
            @Override
            public void uiUpdate() {
                int randomNumber = bubbleFrame.getRandomInt(100);
                if (randomNumber > 50 && randomNumber<74) {
                    Status tweet;
                    int size = tweets.size();
                    synchronized (this) {
                        tweet = tweets.get(randomizer.nextInt(size));
                    }

                    bubbleFrame.addBubble("@" + tweet.getUser().getScreenName(), tweet.getText(), "bubble");

                } else if (randomNumber > 75) {
                    bubbleFrame.addBubble("", "", "empty");
                }
            }
        };
    }


    private TimerTask twitterSearch() {
        return new TimerTask() {
            @Override
            public void run() {
                Twitter twitter = new TwitterFactory().getInstance();
                try {
                    synchronized (this) {
                        Query query = new Query("#YourNSS");
                        //Query query1 = new Query("#YourVoice");
                        Query query2 = new Query("#YourMiddlesexYourVoice");
                        Query query3 = new Query("#MDXNSS");
                        Query query4 = new Query("#MiddlesexNSS");
                        Query query5 = new Query("#YOURNSS");
                        Query query6 = new Query("#yournss");
                        Query query7 = new Query("#Yournss");
                        Query query8 = new Query("#YourNss");
                        Query query9 = new Query("#yourNSS");


                        QueryResult result = twitter.search(query);
                        //QueryResult result1 = twitter.search(query1);
                        QueryResult result2 = twitter.search(query2);
                        QueryResult result3 = twitter.search(query3);
                        QueryResult result4 = twitter.search(query4);
                        QueryResult result5 = twitter.search(query5);
                        QueryResult result6 = twitter.search(query6);
                        QueryResult result7 = twitter.search(query7);
                        QueryResult result8 = twitter.search(query8);
                        QueryResult result9 = twitter.search(query9);


                        tweets = new ArrayList<>();

                        tweets.addAll(result.getTweets());
                        //tweets.addAll(result1.getTweets());
                        tweets.addAll(result2.getTweets());
                        tweets.addAll(result3.getTweets());
                        tweets.addAll(result4.getTweets());
                        tweets.addAll(result5.getTweets());
                        tweets.addAll(result6.getTweets());
                        tweets.addAll(result7.getTweets());
                        tweets.addAll(result8.getTweets());
                        tweets.addAll(result9.getTweets());
                        //System.out.println(tweets.size());

                    }

                } catch (TwitterException te) {
                    te.printStackTrace();
                }
            }
        };
    }

}
