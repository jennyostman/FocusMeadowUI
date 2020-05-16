package exarb.fmui.service;

import exarb.fmui.client.AchievementClient;
import exarb.fmui.model.AchievementWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service for handling achievements
 */
@Service
public class AchievementService {

    private final AchievementClient achievementClient;
    private Map<String, AchievementWeb> allAchievementsMap;

    public AchievementService(AchievementClient achievementClient) {
        this.achievementClient = achievementClient;
        allAchievementsMap = achievementClient.getAllAchievementsMap();
    }

    public List<String> getUsersEarnedAchievementsBackend(String userId){
        return achievementClient.getUsersEarnedAchievements(userId);
    }

    /**
     * Picks out the achievements that ar on the given list
     * @param achievementTypeList - a list containing names of achievements
     * @return List<AchievementWeb> - the achievements on the given list
     */
    public List<AchievementWeb> getEarnedAchievements(List<String> achievementTypeList){
        List<AchievementWeb> achievementList = new ArrayList<>();

        for (String achievement: achievementTypeList) {
            achievementList.add(allAchievementsMap.get(achievement));
        }

        return achievementList;
    }

    /**
     * Picks out the achievements that are not on the given list
     * @param achievementTypeList - a list containing names of achievements
     * @return List<AchievementWeb> - the achievements not on the given list
     */
    public List<AchievementWeb> getUnearnedAchievements(List<String> achievementTypeList){
        List<AchievementWeb> achievementList = new ArrayList<>();

        allAchievementsMap.forEach((k, v) -> {
            if (!achievementTypeList.contains(k)){
                achievementList.add(v);
            }
        });

        return achievementList;
    }
}
