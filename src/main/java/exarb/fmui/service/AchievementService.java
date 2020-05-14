package exarb.fmui.service;

import exarb.fmui.client.AchievementClient;
import exarb.fmui.model.AchievementWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AchievementService {

    private final AchievementClient achievementClient;
    private Map<String, AchievementWeb> allAchievementsMap;

    public AchievementService(AchievementClient achievementClient) {
        this.achievementClient = achievementClient;
        allAchievementsMap = achievementClient.getAllAchievementsMap();
    }

    public List<AchievementWeb> getEarnedAchievements(List<String> achievementTypeList){
        List<AchievementWeb> achievementList = new ArrayList<>();

        for (String achievement: achievementTypeList) {
            achievementList.add(allAchievementsMap.get(achievement));
        }

        return achievementList;
    }

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
