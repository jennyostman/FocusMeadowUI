package exarb.fmui.event;

import exarb.fmui.controller.WebController;
import exarb.fmui.service.AchievementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
    private final WebController webController;
    private final AchievementService achievementService;

    public EventHandler(WebController webController, AchievementService achievementService) {
        this.webController = webController;
        this.achievementService = achievementService;
    }

    /**
     * Listens to the achievement queue
     * @param event, of AchievementEvent class.
     */
    @RabbitListener(queues = "${achievement.queue}")
    void handleNewAchievement(final AchievementEvent event) {
        log.info("Achievement event received: {}", event.getUserAchievementDataId());
        try {

            //TODO handle event
            //webController.achievedAchievements = achievementService.getUsersEarnedAchievementsBackend(webController.userGameData.getUserId());

        } catch (final Exception e) {
            log.error("Error when trying to process AchievementEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
