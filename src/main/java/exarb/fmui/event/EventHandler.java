package exarb.fmui.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);


    /**
     * Listens to the achievement queue, and ...
     * @param event, of AchievementEvent class.
     */
    @RabbitListener(queues = "${achievement.queue}")
    void handleNewAchievement(final AchievementEvent event) {
        log.info("Achievement event received: {}", event.getUserAchievementDataId());
        try {
            System.out.println("event.getUserAchievementDataId(): " + event.getUserAchievementDataId());
            // userAchievementDataService.checkIfAchievement(event.getUserAchievementDataId());
        } catch (final Exception e) {
            log.error("Error when trying to process AchievementEvent", e);
            // The event will not be re-queued and reprocessed repeatedly if
            // something goes wrong.
            // TODO: Since we don’t have anything in place to handle rejected events,
            //  they will be simply discarded. If you want to get deeper into good practices
            //  with RabbitMQ, you can look at how to configure a dead letter exchange and put our
            //  failing messages there for further processing (like retrying, logging, or raising alerts).
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
